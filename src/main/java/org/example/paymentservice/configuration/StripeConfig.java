package org.example.paymentservice.configuration;

import com.razorpay.RazorpayClient;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class RazorPayConfig {
    private String razorPayId;
    private String razorPaySecret;

}
