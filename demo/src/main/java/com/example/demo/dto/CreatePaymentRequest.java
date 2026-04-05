package com.example.demo.dto;

import com.example.demo.entity.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreatePaymentRequest {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private Currency currency;

    private String description;

    @NotBlank
    private String clientId;
}