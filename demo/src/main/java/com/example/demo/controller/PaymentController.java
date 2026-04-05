package com.example.demo.controller;

import com.example.demo.dto.CreatePaymentRequest;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public Map<String, Object> create(@RequestBody @Valid CreatePaymentRequest request) {
        Payment p = service.create(request);

        return Map.of(
                "paymentId", p.getId(),
                "status", p.getStatus()
        );
    }

    @GetMapping("/{id}")
    public PaymentResponse get(@PathVariable Long id) {
        Payment p = service.get(id);

        return map(p);
    }

    @PostMapping("/{id}/confirm")
    public Map<String, Object> confirm(@PathVariable Long id) {
        Payment p = service.confirm(id);

        return Map.of("paymentId", p.getId(), "status", p.getStatus());
    }

    @PostMapping("/{id}/cancel")
    public Map<String, Object> cancel(@PathVariable Long id) {
        Payment p = service.cancel(id);

        return Map.of("paymentId", p.getId(), "status", p.getStatus());
    }

    private PaymentResponse map(Payment p) {
        return PaymentResponse.builder()
                .paymentId(p.getId())
                .amount(p.getAmount())
                .currency(p.getCurrency())
                .description(p.getDescription())
                .clientId(p.getClientId())
                .status(p.getStatus().name())
                .build();
    }
}
