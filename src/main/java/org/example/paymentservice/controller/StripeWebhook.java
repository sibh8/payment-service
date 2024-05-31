package org.example.paymentservice.controller;

import com.stripe.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StripeWebhook {

    @PostMapping("/callback/stripe")
    public void handleStripeEvents(@RequestBody Event event){
        log.info("Recieved the response in the webhook as a part of callback from Stripe");
        log.info("Event Logtype is {}", event.getType());
    }
}
