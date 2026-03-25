package com.irvings.kitchen;

import com.irvings.order.Order;
import com.irvings.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 完整OOD版本：厨房订单同步管理器
 * 职责：管理活跃厨房订单列表，联动OrderStatus更新全局订单状态
 */
@Service
public class KitchenMobileSync {

    private final Map<String, KitchenOrder> activeKitchenOrders = new LinkedHashMap<>();
    private final OrderStatus orderStatus;

    @Autowired
    public KitchenMobileSync(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        System.out.println("[KitchenMobileSync] Initialized");
    }

    /**
     * 接收顾客订单
     */
    public void receiveOrder(Order order) {
        System.out.println("[KitchenMobileSync] receiveOrder(" + order.getOrderId() + ")");
        KitchenOrder ko = new KitchenOrder(order);
        activeKitchenOrders.put(order.getOrderId(), ko);
        // 联动更新全局订单状态
        order.setStatusForStub("IN_PROGRESS");
        orderStatus.updateStatus(order.getOrderId(), "IN_PROGRESS");
    }

    /**
     * 员工开始准备订单
     */
    public void beginPreparation(String orderId) {
        System.out.println("[KitchenMobileSync] beginPreparation(" + orderId + ")");
        KitchenOrder ko = activeKitchenOrders.get(orderId);
        if (ko != null) {
            ko.startPreparing();
            orderStatus.updateStatus(orderId, "PREPARING");
        }
    }

    /**
     * 员工标记订单就绪
     */
    public void markOrderReady(String orderId) {
        System.out.println("[KitchenMobileSync] markOrderReady(" + orderId + ")");
        KitchenOrder ko = activeKitchenOrders.get(orderId);
        if (ko != null) {
            ko.markReady();
            orderStatus.updateStatus(orderId, "READY");
        }
    }

    /**
     * 获取活跃厨房订单列表
     */
    public List<String> getActiveKitchenOrders() {
        return new ArrayList<>(activeKitchenOrders.keySet());
    }

    /**
     * 获取单个厨房订单（可选，用于扩展）
     */
    public KitchenOrder getKitchenOrder(String orderId) {
        return activeKitchenOrders.get(orderId);
    }
}
