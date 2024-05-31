package org.example.paymentservice.service;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.example.paymentservice.configuration.PaymentConfiguration;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorPayPaymentService implements IPaymentService{

    private PaymentConfiguration razorPayConfig;
    private RazorpayClient razorpayClient;

    public RazorPayPaymentService(PaymentConfiguration razorPayConfig, RazorpayClient razorpayClient) {
        this.razorPayConfig = razorPayConfig;
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String doPayment(String email, Integer amount, String phoneNumber, String orderId) throws RazorpayException {

        // Step1: Construct the request body
        JSONObject requestBody = getRazorPayRequestbODY(email, amount, phoneNumber, orderId);

        // Step2: Call API.
        PaymentLink paymentLink = razorpayClient.paymentLink.create(requestBody);

        return paymentLink.toString();
    }

    private JSONObject getRazorPayRequestbODY(String email, Integer amount, String phoneNumber, String orderId) {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", Integer.valueOf(amount));
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", orderId); // Optional

        JSONObject cusInfo = new JSONObject();
        cusInfo.put("phone", phoneNumber);
        cusInfo.put("email", email);

        orderRequest.put("customer", cusInfo);

        JSONObject notify = new JSONObject();
        notify.put("sms", true);
        notify.put("email", true);

        orderRequest.put("notify", notify);
        orderRequest.put("callback_url", "http://localhost:8080/razorpay/webhook");
        orderRequest.put("callback_method", "get");
        return orderRequest;
    }
}
