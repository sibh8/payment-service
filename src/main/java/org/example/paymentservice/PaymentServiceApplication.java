package org.example.paymentservice;

import org.example.paymentservice.configuration.PaymentConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentServiceApplication implements CommandLineRunner {

    private PaymentConfiguration paymentConfiguration;

    public PaymentServiceApplication(PaymentConfiguration paymentConfiguration) {
        this.paymentConfiguration = paymentConfiguration;
    }

    public static void main(String[] args) {

        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Gateway Type: "+paymentConfiguration.getGatewayType());
        System.out.println("Gateway ID: "+paymentConfiguration.getRazorPayConfig().getRazorPayId());
        System.out.println("Gateway Secret: "+paymentConfiguration.getRazorPayConfig().getRazorPaySecret());
//        System.out.println("Stripe ID: "+paymentConfiguration.getStripeConfig().getStripeId());
//        System.out.println("Stripe Secret: "+paymentConfiguration.getStripeConfig().getStripeSecret());
    }
}
