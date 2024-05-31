package org.example.paymentservice.service;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface IPaymentService {

    String doPayment(String email, Integer amount, String phoneNumber, String orderiD) throws RazorpayException, StripeException;
}
