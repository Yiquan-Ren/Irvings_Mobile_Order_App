package com.irvings.order;

import java.util.*;
import java.util.function.Consumer;

public class OrderStatus {

    private final Map<String, String> statusByOrderId = new HashMap<>();
    private final Map<String, List<Consumer<String>>> subscribersByOrderId = new HashMap<>();

    // --- Public API from M02-A01 ---
    public void updateStatus(String orderId, String newStatus) {
        System.out.println("[OrderStatus] updateStatus(" + orderId + ", " + newStatus + ")");
        statusByOrderId.put(orderId, newStatus);

        List<Consumer<String>> subs = subscribersByOrderId.get(orderId);
        if (subs != null) {
            for (Consumer<String> cb : subs) {
                try {
                    cb.accept(newStatus);
                } catch (Exception e) {
                    System.out.println("[OrderStatus] Subscriber callback threw: " + e.getMessage());
                }
            }
        }
    }

    public void subscribe(String orderId, Consumer<String> callback) {
        System.out.println("[OrderStatus] subscribe(" + orderId + ")");
        subscribersByOrderId.computeIfAbsent(orderId, k -> new ArrayList<>()).add(callback);
    }

    // Optional helper for harness visibility
    public String getCurrentStatus(String orderId) {
        return statusByOrderId.get(orderId);
    }
}
