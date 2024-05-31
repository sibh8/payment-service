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
public class PaymentController {

    private IPaymentService razorPay;
    private IPaymentService stripePay;
    private PaymentConfiguration paymentConfiguration;

    public PaymentController(@Qualifier("razorpay") IPaymentService razorPay,
                             @Qualifier("stripe") IPaymentService stripePay,
                             PaymentConfiguration paymentConfiguration) {
        this.razorPay = razorPay;
        this.stripePay = stripePay;
        this.paymentConfiguration = paymentConfiguration;
    }

    @PostMapping("/payments")
    public String initiatePayment(@RequestBody PaymentsRequestDto paymentsRequestDto) throws RazorpayException, StripeException {

        String response = null;

        int gatewayType = getPaymentGatewayType();
        switch(gatewayType){
            case 1:
                response = razorPay.doPayment(paymentsRequestDto.getEmail(),
                        paymentsRequestDto.getAmount(),
                        paymentsRequestDto.getPhoneNumber(),
                        paymentsRequestDto.getOrderId());
            case 2:
                response = stripePay.doPayment(paymentsRequestDto.getEmail(),
                        paymentsRequestDto.getAmount(),
                        paymentsRequestDto.getPhoneNumber(),
                        paymentsRequestDto.getOrderId());
        }

        return response;
    }

    private int getPaymentGatewayType() {

        if (paymentConfiguration.getGatewayType().equals("razorpay"))
            return 1;
        else
            return 2;
    }
}
