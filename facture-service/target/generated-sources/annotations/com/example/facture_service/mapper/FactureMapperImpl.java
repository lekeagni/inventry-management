package com.example.facture_service.mapper;

import com.example.facture_service.dto.FactureDTO;
import com.example.facture_service.model.FactureModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-14T11:41:19+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class FactureMapperImpl implements FactureMapper {

    @Override
    public FactureModel toEntity(FactureDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FactureModel factureModel = new FactureModel();

        factureModel.setFactureId( dto.getFactureId() );
        factureModel.setOrderId( dto.getOrderId() );
        factureModel.setDate( dto.getDate() );
        factureModel.setPrice( dto.getPrice() );
        factureModel.setStatus( dto.getStatus() );

        return factureModel;
    }

    @Override
    public FactureDTO toDTO(FactureModel factureModel) {
        if ( factureModel == null ) {
            return null;
        }

        FactureDTO factureDTO = new FactureDTO();

        factureDTO.setFactureId( factureModel.getFactureId() );
        factureDTO.setOrderId( factureModel.getOrderId() );
        factureDTO.setDate( factureModel.getDate() );
        factureDTO.setPrice( factureModel.getPrice() );
        factureDTO.setStatus( factureModel.getStatus() );

        return factureDTO;
    }
}
