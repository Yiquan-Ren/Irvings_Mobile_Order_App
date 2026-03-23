package payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessing {
    private String gateway = "StubGateway";
    private Map<String, String> transactions = new HashMap<>(); // orderId -> transactionId

    public boolean processPayment(String methodId, double amount) {
        System.out.println("[PaymentProcessing] processPayment(method=" + methodId + ", amount=$" + amount + ")");
        // Simulate successful payment and generate transaction ID
        String txnId = "TXN-" + System.currentTimeMillis();
        transactions.put(txnId, methodId);
        System.out.println("[PaymentProcessing] Payment successful, transactionId=" + txnId);
        return true;
    }

    public boolean refund(String orderId) {
        System.out.println("[PaymentProcessing] refund(order=" + orderId + ")");
        // Simulation refund successful
        return true;
    }
}
