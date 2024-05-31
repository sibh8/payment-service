package org.example.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentsRequestDto {
    private String email;
    private String phoneNumber;
    private Integer amount;
    private String orderId;
}
