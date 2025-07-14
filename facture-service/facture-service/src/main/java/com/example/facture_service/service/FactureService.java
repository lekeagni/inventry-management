package com.example.facture_service.service;

import com.example.facture_service.dto.FactureDTO;

import java.util.List;

public interface FactureService {

    public FactureDTO createFacture(FactureDTO dto);

    public List<FactureDTO> getAllFactures();

    public FactureDTO getFactureById(int factureId);

    public FactureDTO updateFacture(int factureId, FactureDTO dto);

    public Boolean deleteFacture(int factureId);
}
