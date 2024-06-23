package org.example.paymentservice.controller;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.example.paymentservice.configuration.PaymentConfiguration;
import org.example.paymentservice.dto.PaymentsRequestDto;
import org.example.paymentservice.service.IPaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripePaymentController {

    private IPaymentService stripePay;
    private PaymentConfiguration paymentConfiguration;

    public StripePaymentController(@Qualifier("stripe") IPaymentService stripePay,
                                   PaymentConfiguration paymentConfiguration) {
        this.stripePay = stripePay;
        this.paymentConfiguration = paymentConfiguration;
    }

    @PostMapping("/stripe")
    public String initiatePayment(@RequestBody PaymentsRequestDto paymentsRequestDto) throws RazorpayException, StripeException {

        String response = stripePay.doPayment(paymentsRequestDto.getEmail(),
                paymentsRequestDto.getAmount(),
                paymentsRequestDto.getPhoneNumber(),
                paymentsRequestDto.getOrderId());

        return response;
    }
}
