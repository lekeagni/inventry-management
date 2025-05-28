package com.example.facture_service.mapper;

import com.example.facture_service.dto.FactureDTO;
import com.example.facture_service.model.FactureModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FactureMapper {

    FactureModel toEntity(FactureDTO dto);

    FactureDTO toDTO(FactureModel factureModel);
}
