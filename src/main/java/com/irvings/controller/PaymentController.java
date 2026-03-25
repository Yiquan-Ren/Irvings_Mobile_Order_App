package com.irvings.controller;

import com.irvings.payment.PaymentProcessing;
import com.irvings.payment.PaymentReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentProcessing paymentProcessing;

    // 处理支付
    @PostMapping("/process")
    public boolean processPayment(@RequestParam String methodId, @RequestParam double amount) {
        return paymentProcessing.processPayment(methodId, amount);
    }

    // 退款
    @PostMapping("/refund")
    public boolean refund(@RequestParam String orderId) {
        return paymentProcessing.refund(orderId);
    }

    // 生成支付凭证
    @PostMapping("/receipt")
    public PaymentReceipt generateReceipt(@RequestParam String orderId, @RequestParam double amount) {
        return PaymentReceipt.generate(orderId, amount);
    }
}