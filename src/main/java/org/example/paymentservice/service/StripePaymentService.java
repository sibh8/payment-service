package org.example.paymentservice.service;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements IPaymentService{

    private StripeClient stripeClient;

    public StripePaymentService(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public String doPayment(String email, Integer amount, String phoneNumber, String orderiD) throws StripeException {
        PaymentLinkCreateParams  paymentLinkCreateParams = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice("price_1PMRtiSGEiDIIiNRNHFbK386")
                                .setQuantity(1L)
                                .build()
                )
                .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                        .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                .setUrl("https://www.scaler.com")
                                .build())
                        .build())
                .build();

        PaymentLink paymentLink = stripeClient.paymentLinks().create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
