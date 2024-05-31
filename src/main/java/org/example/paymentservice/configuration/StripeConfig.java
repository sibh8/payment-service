package org.example.paymentservice.configuration;

import lombok.Data;

@Data
public class StripeConfig {
    private String stripeId;
    private String stripeSecret;

}
