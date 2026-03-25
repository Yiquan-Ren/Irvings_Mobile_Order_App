package com.irvings.testharness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

// original kitchen flow use these (before refactor)
//import com.irvings.kitchen.KitchenMobileSync;
//import com.irvings.kitchen.KitchenOrder;
import com.irvings.testharness.TestKitchenAdapter.KitchenOrder;
import com.irvings.testharness.TestKitchenAdapter.KitchenMobileSync;
import com.irvings.menu.CustomizationOption;
import com.irvings.menu.MenuItem;
import com.irvings.order.Cart;
import com.irvings.order.ItemUnavailableException;
import com.irvings.order.Order;
import com.irvings.order.OrderAlreadyInProgressException;
import com.irvings.order.OrderDetails;
import com.irvings.payment.PaymentProcessing;
import com.irvings.payment.PaymentReceipt;
import com.irvings.pickup.PickupQueue;
import com.irvings.pickup.PickupReceipt;
import com.irvings.pickup.PickupStatus;
import com.irvings.user.AuthenticationService;
import com.irvings.user.CustomerAccount;

public class IrvingsAppTest {

    // ---------- Test Scenario 1: User Authentication ----------
    @Test
    public void testUserAuthentication() {
        AuthenticationService auth = new AuthenticationService();

        // Register a new user
        boolean registered = auth.register("newuser@psu.edu", "secret");
        assertTrue(registered, "新用户注册应成功");

        // Login with the correct password
        assertTrue(auth.login("test@psu.edu", "password"), "正确密码登录应成功");
        // Login with the incorrect password
        assertFalse(auth.login("test@psu.edu", "wrong"), "错误密码登录应失败");

        // Create session
        String session = auth.createSession("test@psu.edu", "password");
        assertNotNull(session, "会话令牌不应为 null");
        assertTrue(auth.validateSession(session), "会话应有效");

        // Logout
        auth.logout(session);
        assertFalse(auth.validateSession(session), "登出后会话应失效");

        // Create customer account
        CustomerAccount customer = new CustomerAccount("John Doe", "john@example.com", "555-1234");
        assertNotNull(customer.getCustomerId(), "顾客 ID 不应为空");
    }

    // ---------- Test Scenario 2: Menu Browsing ----------
    @Test
    public void testMenuBrowsing() {
        List<MenuItem> menu = MenuItem.getStubMenu();
        assertEquals(4, menu.size(), "菜单应包含 4 个商品");

        MenuItem plain = menu.get(0);
        assertEquals("BAGEL-PLAIN", plain.getItemId());
        assertTrue(plain.isAvailable(), "Plain Bagel 应可用");

        MenuItem soldOut = menu.get(3);
        assertEquals("BAGEL-SOLDOUT", soldOut.getItemId());
        assertFalse(soldOut.isAvailable(), "售罄商品应不可用");

        List<CustomizationOption> options = plain.getCustomizationOptions();
        assertEquals(2, options.size(), "Plain Bagel 应有 2 个定制选项");
    }

    // ---------- Test Scenario 3: Shopping cart operation ----------
    @Test
    public void testCartOperations() throws ItemUnavailableException {
        Cart cart = new Cart();
        assertNotNull(cart.getCartId());

        // Add available items
        Map<String, String> cust = new HashMap<>();
        cust.put("EXTRA-CC", "true");
        String cartItemId = cart.addItem("BAGEL-PLAIN", cust, 2);
        assertNotNull(cartItemId);

        assertEquals(1, cart.getItems().size());

        // Update quantity
        cart.updateQuantity(cartItemId, 3);
        // Calculate the total cost：base 1.75 + extra 0.75 = 2.5 each * 3 = 7.5
        assertEquals(7.5, cart.calculateTotal(), 0.001, "总价计算错误");

        // Remove item
        cart.removeItem(cartItemId);
        assertTrue(cart.getItems().isEmpty());

        // Try adding sold-out items
        assertThrows(ItemUnavailableException.class, () -> {
            cart.addItem("BAGEL-SOLDOUT", null, 1);
        }, "添加售罄商品应抛出异常");
    }

    // ---------- Test Scenario 4: Order Creation and Payment ----------
    @Test
    public void testOrderAndPayment() throws ItemUnavailableException {
        // Create a shopping cart and add items
        Cart cart = new Cart();
        cart.addItem("BAGEL-PLAIN", Map.of("EXTRA-CC", "true"), 2);
        String cartId = cart.getCartId();

        // Create order
        Order order = Order.createFromCart(cartId, "PM-12345678");
        assertNotNull(order);
        assertEquals("PLACED", order.getStatus(), "初始状态应为 PLACED");

        OrderDetails details = order.getDetails();
        assertEquals(cartId, details.getCartId());
        assertEquals(2 * (1.75 + 0.75), details.getTotal(), 0.001);

        // Payment processing
        PaymentProcessing paymentProc = new PaymentProcessing();
        assertTrue(paymentProc.processPayment("PM-12345678", details.getTotal()), "支付应成功");

        // Generate payment vouchers
        PaymentReceipt receipt = PaymentReceipt.generate(details.getOrderId(), details.getTotal());
        assertNotNull(receipt);
        receipt.send("customer@example.com"); // 仅测试方法调用

        // Cancel order (can be cancelled when the status is PLACED)
        try {
            order.cancel();
            assertEquals("CANCELED", order.getStatus(), "订单应被取消");
        } catch (OrderAlreadyInProgressException e) {
            fail("不应抛出异常：" + e.getMessage());
        }
    }

    // ---------- Test Scenario 5: Kitchen and Food Pick-up ----------
    @Test
    public void testKitchenAndPickup() throws ItemUnavailableException {
        // Create a new order
        Cart cart = new Cart();
        cart.addItem("DRINK-COLDBREW", null, 1);
        Order order = Order.createFromCart(cart.getCartId(), "PM-87654321");
        String orderId = order.getOrderId();

        // The kitchen receives and updates the status
        KitchenOrder kitchen = new KitchenOrder();
        kitchen.receive(orderId);
        kitchen.updatePrepStatus(orderId, "PREPARING");
        assertEquals("PREPARING", kitchen.getPrepStatus(orderId));

        // Mobile synchronization
        KitchenMobileSync sync = new KitchenMobileSync();
        sync.syncOrder(orderId);
        sync.pushStatus(orderId, "READY");
        assertNotNull(sync.getLastSync());

        // Pickup queue
        PickupQueue queue = new PickupQueue();
        queue.enqueue(orderId);
        assertEquals(PickupStatus.IN_QUEUE, queue.getStatus(orderId));

        queue.markReady(orderId);
        assertEquals(PickupStatus.READY, queue.getStatus(orderId));

        // Pickup receipt
        PickupReceipt pickupReceipt = PickupReceipt.generateFor(orderId);
        assertNotNull(pickupReceipt);
        assertEquals(orderId, pickupReceipt.getOrderId());
        assertNotNull(pickupReceipt.getPickupCode());

        // Verify the meal pick-up code (according to the generation rule)
        String expectedCode = "P" + (Math.abs(orderId.hashCode()) % 10000);
        assertEquals(expectedCode, pickupReceipt.getPickupCode());

        // Mark that the meal has been collected
        queue.markPickedUp(orderId);
        assertEquals(PickupStatus.PICKED_UP, queue.getStatus(orderId));
    }
}