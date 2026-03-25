package com.irvings.controller;

import com.irvings.pickup.PickupQueue;
import com.irvings.pickup.PickupStatus;
import com.irvings.pickup.PickupReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pickup")
public class PickupController {

    @Autowired
    private PickupQueue pickupQueue;

    // 订单加入取餐队列
    @PostMapping("/enqueue")
    public void enqueueOrder(@RequestParam String orderId) {
        pickupQueue.enqueue(orderId);
    }

    // 获取订单取餐状态
    @GetMapping("/{orderId}/status")
    public PickupStatus getStatus(@PathVariable String orderId) {
        PickupStatus status = pickupQueue.getStatus(orderId);
        if (status == null) throw new IllegalArgumentException("订单不在取餐队列中：" + orderId);
        return status;
    }

    // 标记订单已就绪
    @PutMapping("/{orderId}/ready")
    public void markReady(@PathVariable String orderId) {
        pickupQueue.markReady(orderId);
    }

    // 标记订单已取餐
    @PutMapping("/{orderId}/pickedup")
    public void markPickedUp(@PathVariable String orderId) {
        pickupQueue.markPickedUp(orderId);
    }

    // 获取取餐队列快照
    @GetMapping("/queue")
    public List<String> getQueue() {
        return pickupQueue.getQueueSnapshot();
    }

    // 生成取餐码
    @GetMapping("/receipt/{orderId}")
    public PickupReceipt generateReceipt(@PathVariable String orderId) {
        return PickupReceipt.generateFor(orderId);
    }
}
