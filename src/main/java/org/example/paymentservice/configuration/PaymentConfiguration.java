package org.example.paymentservice.configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.StripeClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app.payment")
@Data
public class PaymentConfiguration {
    private String gatewayType;
    private RazorPayConfig razorPayConfig;
    private StripeConfig stripeConfig;

    @Bean
    public RazorpayClient getRazorpayClient() throws RazorpayException {
        return new RazorpayClient(razorPayConfig.getRazorPayId(), razorPayConfig.getRazorPaySecret());
    }

    @Bean
    public StripeClient getStripeClient(){
        return new StripeClient(stripeConfig.getStripeSecret());
    }
}
