package com.example.facture_service.repository;

import com.example.facture_service.model.FactureModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<FactureModel, Integer> {
}
