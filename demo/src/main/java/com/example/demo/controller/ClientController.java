package com.example.demo.controller;

import com.example.demo.dto.PaymentResponse;
import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final PaymentService service;

    @GetMapping("/{clientId}/payments")
    public List<PaymentResponse> get(@PathVariable String clientId) {
        return service.getByClient(clientId)
                .stream()
                .map(p -> PaymentResponse.builder()
                        .paymentId(p.getId())
                        .amount(p.getAmount())
                        .currency(p.getCurrency())
                        .status(p.getStatus().name())
                        .build())
                .toList();
    }
}