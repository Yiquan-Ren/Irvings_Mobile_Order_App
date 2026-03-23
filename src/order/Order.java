package order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String orderId;
    private final String cartId;
    private final String paymentMethodId;
    private String status; // e.g., PLACED, IN_PROGRESS, READY, PICKED_UP, CANCELED
    private final LocalDateTime createdAt;
    private final List<CartItem> itemsSnapshot;
    private final double total;

    private Order(String orderId, String cartId, String paymentMethodId, String status,
                  LocalDateTime createdAt, List<CartItem> itemsSnapshot, double total) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.paymentMethodId = paymentMethodId;
        this.status = status;
        this.createdAt = createdAt;
        this.itemsSnapshot = itemsSnapshot;
        this.total = total;
    }

    // --- Public API from M02-A01 ---
    public static Order createFromCart(String cartId, String paymentMethodId) {
        System.out.println("[Order] createFromCart(cartId=" + cartId + ", paymentMethodId=" + paymentMethodId + ")");

        Cart cart = Cart.getCartById(cartId);
        List<CartItem> snapshot = new ArrayList<>();
        double total = 0.0;

        if (cart != null) {
            snapshot = cart.getItems();
            total = cart.calculateTotal();
        } else {
            System.out.println("[Order] WARNING: cart not found for cartId=" + cartId + " (using empty snapshot)");
        }

        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return new Order(orderId, cartId, paymentMethodId, "PLACED", LocalDateTime.now(), snapshot, total);
    }

    public String getStatus() {
        System.out.println("[Order] getStatus(" + orderId + ") = " + status);
        return status;
    }

    public void cancel() throws OrderAlreadyInProgressException {
        System.out.println("[Order] cancel(" + orderId + ") called; current=" + status);

        // Stub rule: only allow cancel if still PLACED
        if (!"PLACED".equals(status)) {
            throw new OrderAlreadyInProgressException("Order " + orderId + " cannot be canceled because status=" + status);
        }
        status = "CANCELED";
    }

    public OrderDetails getDetails() {
        System.out.println("[Order] getDetails(" + orderId + ")");
        return new OrderDetails(orderId, cartId, paymentMethodId, total, createdAt, itemsSnapshot);
    }

    // --- Optional helper
    public String getOrderId() { return orderId; }

    /** For other stubs to simulate progress (kitchen/pickup) */
    public void setStatusForStub(String newStatus) {
        System.out.println("[Order] setStatusForStub(" + orderId + "): " + status + " -> " + newStatus);
        this.status = newStatus;
    }
}
