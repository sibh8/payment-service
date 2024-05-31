package org.example.paymentservice.controller;

import com.razorpay.RazorpayException;
import org.example.paymentservice.dto.PaymentsRequestDto;
import org.example.paymentservice.service.IPaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RazorpayPaymentController {

    private IPaymentService razorPay;
    private IPaymentService stripePay;

    public RazorpayPaymentController(@Qualifier("razorpay") IPaymentService razorPay,
                                     @Qualifier("stripe") IPaymentService stripePay) {
        this.razorPay = razorPay;
        this.stripePay = stripePay;
    }

    @PostMapping("/payments")
    public String initiatePayment(@RequestBody PaymentsRequestDto paymentsRequestDto) throws RazorpayException {

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
        return 1;
    }

    ;
}
