package com.example.facture_service.exception;

public class FactureNotFoundException extends CustomException {
    public FactureNotFoundException(Integer factureId) {

        super("facture not found with " + factureId);
    }
}
