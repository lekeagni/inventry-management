package com.example.facture_service.controller;

import com.example.facture_service.dto.FactureDTO;
import com.example.facture_service.service.FactureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facture")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {

        this.factureService = factureService;
    }

    @PostMapping("/save")
    @Operation(summary = "create new invoice", description = "save the invoice in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "save invoice successful"),
            @ApiResponse(responseCode = "400", description = "failed to save invoice"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<FactureDTO> createFac(@RequestBody FactureDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.factureService.createFacture(dto));
    }

    @GetMapping()
    public ResponseEntity<List<FactureDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.factureService.getAllFactures());
    }

    @GetMapping("/{factureId}")
    public ResponseEntity<FactureDTO> getFacture(@PathVariable int factureId){
        return ResponseEntity.status(HttpStatus.OK).body(this.factureService.getFactureById(factureId));
    }

    @DeleteMapping("/delete/{factureId}")
    public ResponseEntity<Boolean> delete(@PathVariable int factureId){
        boolean isDeleted = this.factureService.deleteFacture(factureId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
