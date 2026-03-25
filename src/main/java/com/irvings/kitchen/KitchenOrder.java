package com.irvings.kitchen;

import com.irvings.order.Order;
import java.time.LocalDateTime;

/**
 * 完整OOD版本：单个厨房订单的实体类
 * 职责：管理单个订单的厨房状态流转
 */
public class KitchenOrder {

    private final String orderId;
    private String kitchenStatus; // RECEIVED, PREPARING, READY
    private final LocalDateTime receivedAt;

    // OOD设计：构造器接收Order对象，而不是单纯的orderId
    public KitchenOrder(Order order) {
        this.orderId = order.getOrderId();
        this.kitchenStatus = "RECEIVED";
        this.receivedAt = LocalDateTime.now();
        System.out.println("[KitchenOrder] Received order " + orderId);
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getKitchenStatus() {
        return kitchenStatus;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    // 业务方法：开始准备
    public void startPreparing() {
        System.out.println("[KitchenOrder] startPreparing(" + orderId + ")");
        this.kitchenStatus = "PREPARING";
    }

    // 业务方法：标记就绪
    public void markReady() {
        System.out.println("[KitchenOrder] markReady(" + orderId + ")");
        this.kitchenStatus = "READY";
    }
}