package com.irvings.menu;

import java.util.ArrayList;
import java.util.Collections;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu_items")//对应H2数据库中的表名

public class MenuItem {

    @Id
    @Column(name = "item_id")//对应H2数据库中的列名
    private String itemId;

    private String category;
    private String name;
    private String description;

    @Column(name = "base_price")//对应H2数据库中的列名
    private double basePrice;

    @Column(name = "price_label")
    private String priceLabel;

    private boolean available;

    @Column(name = "customization_options", columnDefinition = "json")
    private List<CustomizationOption> customizationOptions = new ArrayList<>();


    // 自动更新时间戳（替代Supabase触发器）
    @Column(name = "created_at", updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }

    // ==============================================
    // 无参构造器（JPA 需要）
    // ==============================================
    public MenuItem() {}

    public MenuItem(String itemId, String name, double basePrice, boolean available) {
        this.itemId = itemId;
        this.name = name;
        this.basePrice = basePrice;
        this.available = available;
        this.category = "Uncategorized"; // 默认值
        this.description = ""; // 默认值
    }

    // 完整的 Getters 和 Setters（所有字段）
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }

    public String getPriceLabel() { return priceLabel; }
    public void setPriceLabel(String priceLabel) { this.priceLabel = priceLabel; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public List<CustomizationOption> getCustomizationOptions() { return customizationOptions; }
    public void setCustomizationOptions(List<CustomizationOption> customizationOptions) {
        this.customizationOptions = customizationOptions;
    }


    public void setAvailability(boolean available) {
        System.out.println("[MenuItem] setAvailability(" + itemId + "): " + this.available + " -> " + available);
        this.available = available;
    }

    public void addCustomizationOption(CustomizationOption option) {
        customizationOptions.add(option);
    }

    // 保留你原来的 getStubMenu() 方法（兼容现有测试）
    // 但现在优先从数据库读取，Stub 只作为后备
    // ==============================================
    public static List<MenuItem> getStubMenu() {
        List<MenuItem> menu = new ArrayList<>();
        // 你原来的 Stub 菜单代码（保留，用于测试）
        menu.add(new MenuItem("BAGEL-PLAIN", "Plain Bagel", 1.50, true));
        menu.add(new MenuItem("DRINK-COLDBREW", "Cold Brew", 3.25, true));
        return menu;
    }
}

