package com.irvings.kitchen;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class KitchenMobileSync {
    private int syncInterval = 30; // seconds
    private LocalDateTime lastSync;
    private Map<String, String> lastPushedStatus = new HashMap<>();

    public void syncOrder(String orderId) {
        System.out.println("[KitchenMobileSync] syncOrder(order=" + orderId + ")");
        lastSync = LocalDateTime.now();
    }

    public void pushStatus(String orderId, String status) {
        System.out.println("[KitchenMobileSync] pushStatus(order=" + orderId + ", status=" + status + ")");
        lastPushedStatus.put(orderId, status);
    }

    public LocalDateTime getLastSync() { return lastSync; }
}
