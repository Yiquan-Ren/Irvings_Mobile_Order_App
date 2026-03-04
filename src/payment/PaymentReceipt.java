package payment;

import java.time.LocalDateTime;

public class PaymentReceipt {
    private final String receiptId;
    private final String orderId;
    private final double amount;
    private final LocalDateTime timestamp;

    private PaymentReceipt(String receiptId, String orderId, double amount, LocalDateTime timestamp) {
        this.receiptId = receiptId;
        this.orderId = orderId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public static PaymentReceipt generate(String orderId, double amount) {
        System.out.println("[PaymentReceipt] generate(order=" + orderId + ", amount=$" + amount + ")");
        String receiptId = "RCPT-" + System.currentTimeMillis();
        return new PaymentReceipt(receiptId, orderId, amount, LocalDateTime.now());
    }

    public void send(String email) {
        System.out.println("[PaymentReceipt] send receipt " + receiptId + " to " + email);
    }

    public String getReceiptId() { return receiptId; }
    public String getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}