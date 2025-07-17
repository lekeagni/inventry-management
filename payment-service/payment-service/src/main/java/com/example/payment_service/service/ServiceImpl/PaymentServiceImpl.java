package com.example.payment_service.service.ServiceImpl;

import com.example.payment_service.config.PaymentPropertiesConfig;
import com.example.payment_service.dto.*;
import com.example.payment_service.feign.FactureFeignClient;
import com.example.payment_service.feign.OrderFeignClient;
import com.example.payment_service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentPropertiesConfig paymentPropertiesConfig;
    private final FactureFeignClient factureFeignClient;
    private final OrderFeignClient orderFeignClient;

    public PaymentServiceImpl(PaymentPropertiesConfig paymentPropertiesConfig,
                              FactureFeignClient factureFeignClient,
                              OrderFeignClient orderFeignClient) {
        this.paymentPropertiesConfig = paymentPropertiesConfig;
        this.factureFeignClient = factureFeignClient;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {

        if (dto.getAmount() == null || dto.getAmount() <= 0) {
            log.warn("Montant invalide : {}", dto.getAmount());
            return new PaymentResponseDTO("PAYMENT_FAIL", null);
        }

        String reference = UUID.randomUUID().toString();
        String method = dto.getPayment_method().toUpperCase();

        try {
            // Processus de paiement
            switch (method) {
                case "PAYPAL":
                    processPaypal(dto.getAmount(), reference);
                    break;
                case "MTN_MOMO":
                    processMomo(dto.getAmount(), reference);
                    break;
                case "ORANGE":
                    processOrange(dto.getAmount(), reference);
                    break;
                default:
                    log.error("Mode de paiement inconnu : {}", method);
                    return new PaymentResponseDTO("FAILURE", null);
            }

            factureFeignClient.updateFactureStatus(dto.getFactureId(), new UpdateFactureStatutsDTO("PAID"));
            orderFeignClient.updateOrderStatusByFacture(dto.getFactureId(), new UpdateOrderStatutsDTO("LIVREE"));

            log.info("Paiement réussi et statuts mis à jour pour facture {}", dto.getFactureId());

            return new PaymentResponseDTO("SUCCESS", reference);

        } catch (Exception e) {
            log.error("Erreur lors du traitement du paiement ou des services : {}", e.getMessage());
            return new PaymentResponseDTO("FAILURE", null);
        }
    }

    private void processPaypal(Double amount, String ref) {
        String clientId = paymentPropertiesConfig.getPaypal().getClientId();
        String clientSecret = paymentPropertiesConfig.getPaypal().getClientSecret();

        log.info("Paiement PAYPAL : amount={} ref={} clientId={}", amount, ref, clientId);
    }

    private void processMomo(Double amount, String ref) {
        String apiKey = paymentPropertiesConfig.getMomo().getApiKey();
        String apiSecret = paymentPropertiesConfig.getMomo().getApiSecret();

        log.info("Paiement MTN MOMO : amount={} ref={} apiKey={}", amount, ref, apiKey);
    }

    private void processOrange(Double amount, String ref) {
        String apiKey = paymentPropertiesConfig.getOrange().getApiKey();
        String apiSecret = paymentPropertiesConfig.getOrange().getApiSecret();

        log.info("Paiement ORANGE MONEY : amount={} ref={} apiKey={}", amount, ref, apiKey);
    }
}
