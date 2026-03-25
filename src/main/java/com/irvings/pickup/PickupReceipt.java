package com.irvings.pickup;

import java.time.LocalDateTime;

public class PickupReceipt {
    private final String orderId;
    private final String pickupCode;
    private final LocalDateTime createdAt;
    private final String instructions;

    public PickupReceipt(String orderId, String pickupCode, String instructions) {
        this.orderId = orderId;
        this.pickupCode = pickupCode;
        this.createdAt = LocalDateTime.now();
        this.instructions = instructions;
    }

    public static PickupReceipt generateFor(String orderId) {
        System.out.println("[PickupReceipt] generateFor(" + orderId + ")");
        String code = "P" + (Math.abs(orderId.hashCode()) % 10000);
        return new PickupReceipt(orderId, code, "Show this code at the pickup counter.");
    }

    public String getOrderId() { return orderId; }
    public String getPickupCode() { return pickupCode; }
    public String getInstructions() { return instructions; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

