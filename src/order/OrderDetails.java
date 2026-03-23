package order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class OrderDetails {
    private final String orderId;
    private final String cartId;
    private final String paymentMethodId;
    private final double total;
    private final LocalDateTime createdAt;
    private final List<CartItem> itemsSnapshot;

    public OrderDetails(String orderId, String cartId, String paymentMethodId, double total,
                        LocalDateTime createdAt, List<CartItem> itemsSnapshot) {
        this.orderId = orderId;
        this.cartId = cartId;
        this.paymentMethodId = paymentMethodId;
        this.total = total;
        this.createdAt = createdAt;
        this.itemsSnapshot = Collections.unmodifiableList(itemsSnapshot);
    }

    public String getOrderId() { return orderId; }
    public String getCartId() { return cartId; }
    public String getPaymentMethodId() { return paymentMethodId; }
    public double getTotal() { return total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<CartItem> getItemsSnapshot() { return itemsSnapshot; }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId='" + orderId + '\'' +
                ", cartId='" + cartId + '\'' +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                ", total=" + total +
                ", createdAt=" + createdAt +
                ", itemsSnapshot=" + itemsSnapshot +
                '}';
    }
}

