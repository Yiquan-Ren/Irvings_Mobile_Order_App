package com.irvings.rewards;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    private String id;
    private String name;

    @Column(name = "points_required")
    private int pointsRequired;

    private String description;

    @Column(name = "cart_item_id")
    private String cartItemId;

    // 自动更新时间戳
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
    public Reward() {}

    // 业务构造器
    public Reward(String id, String name, int pointsRequired, String description, String cartItemId) {
        this.id = id;
        this.name = name;
        this.pointsRequired = pointsRequired;
        this.description = description;
        this.cartItemId = cartItemId;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getPointsRequired() { return pointsRequired; }
    public String getDescription() { return description; }
    public String getCartItemId() { return cartItemId; }
}
