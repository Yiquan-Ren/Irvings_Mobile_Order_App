package com.irvings.order;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cart_item_id")
    private String cartItemId;

    @Column(name = "item_id")
    private String itemId;

    private int quantity;

     // 数据库存储用的可变 Map（JPA 需要）
    @Column(columnDefinition = "json")
    private Map<String, String> selections = new HashMap<>();
        // 供外部使用的不可变 Map（保持原有逻辑）

    // 自动更新时间戳（替代 Supabase 触发器）
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // 无参构造器（JPA 需要）
    public CartItem() {}

    public CartItem(String cartItemId, String itemId, Map<String, String> customization, int quantity) {
        this.cartItemId = cartItemId;
        this.itemId = itemId;

         // 保留你原来的 unmodifiableMap 逻辑
        Map<String, String> safeCustomization = customization == null 
                ? Collections.emptyMap() 
                : Collections.unmodifiableMap(customization);
        // 同时给数据库存储用的 selections 赋值（JPA 需要可变 Map）
        this.selections = new HashMap<>(safeCustomization);
        this.quantity = quantity;

        this.selections = customization == null ? Collections.emptyMap() : Collections.unmodifiableMap(customization);
        this.quantity = quantity;
    }

    public String getCartItemId() { return cartItemId; }
    public String getItemId() { return itemId; }
    public Map<String, String> getCustomization() { return Collections.unmodifiableMap(selections); }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId='" + cartItemId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", customization=" + getCustomization() +
                ", quantity=" + quantity +
                '}';
    }
}

