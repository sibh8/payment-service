package org.example.paymentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RazorPayWebhook {

    @GetMapping("/razorpay/webhook")
    public String razorPayCallback(){
        return "redirecting customer.....";
    }
}
