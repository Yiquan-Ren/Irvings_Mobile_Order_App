package order;

import menu.MenuItem;
import java.util.*;

public class Cart {

    // Registry so Order.createFromCart(cartId, ...) can find the cart (stub integration)
    private static final Map<String, Cart> CART_REGISTRY = new HashMap<>();

    private final String cartId;
    private final Map<String, CartItem> itemsById = new LinkedHashMap<>();

    public Cart() {
        this.cartId = "CART-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        CART_REGISTRY.put(cartId, this);
        System.out.println("[Cart] Created cart " + cartId);
    }

    /** Helper for other stubs to find cart by id */
    public static Cart getCartById(String cartId) {
        return CART_REGISTRY.get(cartId);
    }

    /** helpful for harness/integration */
    public String getCartId() {
        return cartId;
    }

    // --- Public API from M02-A01 ---
    public String addItem(String itemId, Map<String, String> customization, int quantity)
            throws ItemUnavailableException {

        System.out.println("[Cart] addItem(itemId=" + itemId + ", qty=" + quantity + ") on " + cartId);

        if (quantity <= 0) quantity = 1;

        MenuItem mi = findMenuItem(itemId);
        if (mi == null) {
            throw new ItemUnavailableException("ItemId not found: " + itemId);
        }
        if (!mi.isAvailable()) {
            throw new ItemUnavailableException("Item is unavailable: " + itemId);
        }

        String cartItemId = "CI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        CartItem cartItem = new CartItem(cartItemId, itemId, customization, quantity);
        itemsById.put(cartItemId, cartItem);

        return cartItemId;
    }

    public void removeItem(String cartItemId) {
        System.out.println("[Cart] removeItem(" + cartItemId + ") on " + cartId);
        itemsById.remove(cartItemId);
    }

    public void updateQuantity(String cartItemId, int newQuantity) {
        System.out.println("[Cart] updateQuantity(" + cartItemId + ", " + newQuantity + ") on " + cartId);
        CartItem item = itemsById.get(cartItemId);
        if (item != null) {
            item.setQuantity(Math.max(1, newQuantity));
        }
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(itemsById.values());
    }

    public double calculateTotal() {
        double total = 0.0;

        for (CartItem ci : itemsById.values()) {
            MenuItem mi = findMenuItem(ci.getItemId());
            if (mi == null) continue; // stub: skip unknowns

            double base = mi.getBasePrice();
            double customizationDelta = estimateCustomizationDelta(ci.getCustomization());
            total += (base + customizationDelta) * ci.getQuantity();
        }

        total = Math.round(total * 100.0) / 100.0;
        System.out.println("[Cart] calculateTotal() on " + cartId + " = $" + total);
        return total;
    }

    public void clear() {
        System.out.println("[Cart] clear() on " + cartId);
        itemsById.clear();
    }

    // --- Internal helpers (stub logic) ---
    private MenuItem findMenuItem(String itemId) {
        for (MenuItem mi : MenuItem.getStubMenu()) {
            if (mi.getItemId().equals(itemId)) return mi;
        }
        return null;
    }

    /**
     * Stub rule: if customization contains known option IDs, add small deltas.
     */
    private double estimateCustomizationDelta(Map<String, String> customization) {
        if (customization == null || customization.isEmpty()) return 0.0;

        double delta = 0.0;
        for (String key : customization.keySet()) {
            if (key == null) continue;
            switch (key) {
                case "EXTRA-CC" -> delta += 0.75;
                case "CC-SCALLION" -> delta += 0.80;
                case "LOX" -> delta += 2.50;
                default -> delta += 0.25; // generic small delta for “some customization”
            }
        }
        return delta;
    }
}

