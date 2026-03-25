package com.irvings.controller;

import com.irvings.order.Order;
import com.irvings.order.OrderDetails;
import com.irvings.order.OrderAlreadyInProgressException;
import com.irvings.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Consumer;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderStatus orderStatus;

    // 从购物车创建订单
    @PostMapping("/create")
    public Order createOrder(@RequestParam String cartId, @RequestParam String paymentMethodId) {
        Order order = Order.createFromCart(cartId, paymentMethodId);
        orderStatus.updateStatus(order.getOrderId(), order.getStatus());
        return order;
    }

    // 获取订单状态
    @GetMapping("/{orderId}/status")
    public String getStatus(@PathVariable String orderId) {
        return orderStatus.getCurrentStatus(orderId);
    }

    /*
    // 取消订单
    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable String orderId) throws OrderAlreadyInProgressException {
        Order order = new Order(orderId, null, null, orderStatus.getCurrentStatus(orderId), null, null, 0);
        order.cancel();
        orderStatus.updateStatus(orderId, "CANCELED");
    }

    // 获取订单详情
    @GetMapping("/{orderId}/details")
    public OrderDetails getDetails(@PathVariable String orderId) {
        Order order = new Order(orderId, null, null, null, null, null, 0);
        return order.getDetails();
    }
    */

    // 取消订单
    @PostMapping("/{orderId}/cancel")
    public void cancelOrder(@PathVariable String orderId) throws OrderAlreadyInProgressException {
        // 注意：这里为了复用原有逻辑，我们通过状态重建一个 Order 对象
        String currentStatus = orderStatus.getCurrentStatus(orderId);
        if (currentStatus == null) throw new IllegalArgumentException("订单不存在：" + orderId);
        
        // 这里我们用反射或简单方式调用 cancel，实际项目建议 Order 也交给 Spring 管理
        // 这里为了最小改动，我们直接模拟取消逻辑
        if (!"PLACED".equals(currentStatus)) {
            throw new OrderAlreadyInProgressException("Order " + orderId + " cannot be canceled because status=" + currentStatus);
        }
        orderStatus.updateStatus(orderId, "CANCELED");
    }

    // 获取订单详情
    @GetMapping("/{orderId}/details")
    public OrderDetails getDetails(@PathVariable String orderId) {
        // 注意：原有 Order 是有状态的，这里为了演示，我们假设通过 orderId 能获取
        // 实际项目建议引入 OrderRepository 存储订单
        // 这里我们返回一个简单的模拟详情，或者你可以扩展原有 Order 类
        throw new UnsupportedOperationException("订单详情持久化功能需要扩展原有 Order 类，请先将订单存入数据库或内存 Map");
    }

    // 订阅订单状态变更
    @PostMapping("/{orderId}/subscribe")
    public void subscribeStatus(@PathVariable String orderId, Consumer<String> callback) {
        orderStatus.subscribe(orderId, callback);
    }
}