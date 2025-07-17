package com.example.payment_service.controller;

import com.example.payment_service.dto.FactureDTO;
import com.example.payment_service.feign.FactureFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("facture")
@Tag(name = "get invoice", description = "get the invoice in database by id")
public class FactureTestController {

    private final FactureFeignClient factureFeignClient;

    public FactureTestController(FactureFeignClient factureFeignClient) {
        this.factureFeignClient = factureFeignClient;
    }

    @GetMapping("/facture/{factureId}")
    @Operation(summary = "get invoice", description = "get invoice in the database by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get invoice successful"),
            @ApiResponse(responseCode = "400", description = "failed to get user"),
            @ApiResponse(responseCode = "500", description = "error server")
    })

    public ResponseEntity<FactureDTO> get(@PathVariable int factureId){
        return ResponseEntity.status(HttpStatus.OK).body(this.factureFeignClient.getFactureById(factureId));
    }
}
