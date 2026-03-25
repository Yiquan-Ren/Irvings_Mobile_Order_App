package com.irvings.payment;

import java.util.UUID;

public class PaymentMethod {
    private final String methodId;
    private final String customerId;
    private String type;           // e.g., "credit", "debit", "gift"
    private boolean isDefault;

    public PaymentMethod(String customerId, String type, boolean isDefault) {
        this.methodId = "PM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.customerId = customerId;
        this.type = type;
        this.isDefault = isDefault;
        System.out.println("[PaymentMethod] Created " + methodId + " for customer " + customerId);
    }

    public String getMethodId() { return methodId; }
    public String getCustomerId() { return customerId; }
    public String getType() { return type; }
    public boolean isDefault() { return isDefault; }

    public void setDefault(boolean isDefault) {
        System.out.println("[PaymentMethod] setDefault(" + methodId + "): " + this.isDefault + " -> " + isDefault);
        this.isDefault = isDefault;
    }

    public String getDetails() {
        return "PaymentMethod{id=" + methodId + ", customer=" + customerId + ", type=" + type + ", default=" + isDefault + "}";
    }
}