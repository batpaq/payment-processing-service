package com.example.demo.service;

import com.example.demo.dto.CreatePaymentRequest;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PaymentStatus;
import com.example.demo.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public Payment create(CreatePaymentRequest request) {
        Payment payment = Payment.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .description(request.getDescription())
                .clientId(request.getClientId())
                .status(PaymentStatus.PENDING)
                .build();

        return repository.save(payment);
    }

    public Payment get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    public Payment confirm(Long id) {
        Payment p = get(id);

        if (p.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Payment cannot be confirmed");
        }

        p.setStatus(PaymentStatus.CONFIRMED);
        return repository.save(p);
    }

    public Payment cancel(Long id) {
        Payment p = get(id);

        if (p.getStatus() != PaymentStatus.PENDING) {
            throw new RuntimeException("Payment cannot be canceled");
        }

        p.setStatus(PaymentStatus.CANCELED);
        return repository.save(p);
    }

    public List<Payment> getByClient(String clientId) {
        return repository.findByClientId(clientId);
    }
}
