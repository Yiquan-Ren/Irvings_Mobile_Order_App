package com.irvings.kitchen;

import java.util.HashMap;
import java.util.Map;

public class KitchenOrder {
    private Map<String, String> prepStatusMap = new HashMap<>(); // orderId -> status

    public void receive(String orderId) {
        System.out.println("[KitchenOrder] receive(order=" + orderId + ")");
        prepStatusMap.put(orderId, "RECEIVED");
    }

    public void updatePrepStatus(String orderId, String newStatus) {
        System.out.println("[KitchenOrder] updatePrepStatus(order=" + orderId + ", status=" + newStatus + ")");
        prepStatusMap.put(orderId, newStatus);
    }

    public String getPrepStatus(String orderId) {
        return prepStatusMap.get(orderId);
    }
}