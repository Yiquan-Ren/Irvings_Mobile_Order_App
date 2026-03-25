package com.irvings.order;

import java.util.Collections;
import java.util.Map;

public class CartItem {
    private final String cartItemId;
    private final String itemId;
    private final Map<String, String> customization; // optionId -> chosen value
    private int quantity;

    public CartItem(String cartItemId, String itemId, Map<String, String> customization, int quantity) {
        this.cartItemId = cartItemId;
        this.itemId = itemId;
        this.customization = customization == null ? Collections.emptyMap() : Collections.unmodifiableMap(customization);
        this.quantity = quantity;
    }

    public String getCartItemId() { return cartItemId; }
    public String getItemId() { return itemId; }
    public Map<String, String> getCustomization() { return customization; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", customization=" + customization +
                ", quantity=" + quantity +
                '}';
    }
}

