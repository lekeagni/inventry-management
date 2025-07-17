package com.example.payment_service.service;

import com.example.payment_service.config.PaymentPropertiesConfig;
import com.example.payment_service.dto.PaymentRequestDTO;
import com.example.payment_service.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public PaymentResponseDTO processPayment(PaymentRequestDTO dto);
}
