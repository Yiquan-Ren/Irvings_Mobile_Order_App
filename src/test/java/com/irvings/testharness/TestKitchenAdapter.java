package com.irvings.testharness;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试适配器：完全兼容你现有的JUnit测试用例调用方式
 * 仅用于测试，不参与实际API业务逻辑
 */
public class TestKitchenAdapter {

    // 模拟原简化版KitchenOrder的行为
    public static class KitchenOrder {
        private Map<String, String> prepStatusMap = new HashMap<>();

        public void receive(String orderId) {
            System.out.println("[TestKitchenOrder] receive(order=" + orderId + ")");
            prepStatusMap.put(orderId, "RECEIVED");
        }

        public void updatePrepStatus(String orderId, String newStatus) {
            System.out.println("[TestKitchenOrder] updatePrepStatus(order=" + orderId + ", status=" + newStatus + ")");
            prepStatusMap.put(orderId, newStatus);
        }

        public String getPrepStatus(String orderId) {
            return prepStatusMap.get(orderId);
        }
    }

    // 模拟原简化版KitchenMobileSync的行为
    public static class KitchenMobileSync {
        private java.time.LocalDateTime lastSync;
        private Map<String, String> lastPushedStatus = new HashMap<>();

        public void syncOrder(String orderId) {
            System.out.println("[TestKitchenMobileSync] syncOrder(order=" + orderId + ")");
            lastSync = java.time.LocalDateTime.now();
        }

        public void pushStatus(String orderId, String status) {
            System.out.println("[TestKitchenMobileSync] pushStatus(order=" + orderId + ", status=" + status + ")");
            lastPushedStatus.put(orderId, status);
        }

        public java.time.LocalDateTime getLastSync() {
            return lastSync;
        }
    }
}
