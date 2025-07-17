package com.example.payment_service.feign;

import com.example.payment_service.dto.FactureDTO;
import com.example.payment_service.dto.UpdateFactureStatutsDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "facture-service")
public interface FactureFeignClient {

    @GetMapping("/facture/{factureId}")
    FactureDTO getFactureById(@PathVariable int factureId);

    @PutMapping("/facture/{}/statuts")
    void updateFactureStatus(@PathVariable("factureId") int factureId, @RequestBody UpdateFactureStatutsDTO dto);
}
