package com.example.demo.dto;

import com.example.demo.entity.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Double amount;
    private Currency currency;
    private String description;
    private String clientId;
    private String status;
}