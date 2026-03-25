package com.irvings.controller;

import com.irvings.kitchen.KitchenMobileSync;
import com.irvings.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {

    @Autowired
    private KitchenMobileSync kitchenMobileSync;

    // 厨房接收订单
    @PostMapping("/receive")
    public void receiveOrder(@RequestBody Order order) {
        kitchenMobileSync.receiveOrder(order);
    }

    // 开始准备订单
    @PutMapping("/{orderId}/preparing")
    public void beginPreparation(@PathVariable String orderId) {
        kitchenMobileSync.beginPreparation(orderId);
    }

    // 标记订单已就绪
    @PutMapping("/{orderId}/ready")
    public void markOrderReady(@PathVariable String orderId) {
        kitchenMobileSync.markOrderReady(orderId);
    }

    // 获取厨房活跃订单列表
    @GetMapping("/active")
    public List<String> getActiveOrders() {
        return kitchenMobileSync.getActiveKitchenOrders();
    }
}