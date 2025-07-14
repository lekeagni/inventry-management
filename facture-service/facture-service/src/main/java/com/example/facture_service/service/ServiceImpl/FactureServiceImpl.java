package com.example.facture_service.service.ServiceImpl;

import com.example.facture_service.dto.FactureDTO;
import com.example.facture_service.exception.FactureNotFoundException;
import com.example.facture_service.feign.OrderFeignClient;
import com.example.facture_service.mapper.FactureMapper;
import com.example.facture_service.model.FactureModel;
import com.example.facture_service.repository.FactureRepository;
import com.example.facture_service.service.FactureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final FactureMapper factureMapper;
    private final OrderFeignClient orderFeignClient;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, OrderFeignClient orderFeignClient) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public FactureDTO createFacture(FactureDTO dto) {

        return null;
    }

    @Override
    public List<FactureDTO> getAllFactures() {
        List<FactureModel> exist = this.factureRepository.findAll();
        return exist.stream().map(factureMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public FactureDTO getFactureById(int factureId) {
        FactureModel factureModel = this.factureRepository.findById(factureId)
                .orElseThrow(()->new FactureNotFoundException(factureId));
        return factureMapper.toDTO(factureModel);
    }

    @Override
    public FactureDTO updateFacture(int factureId, FactureDTO dto) {
        return null;
    }

    @Override
    public Boolean deleteFacture(int factureId) {
        Optional<FactureModel> check  = this.factureRepository.findById(factureId);
        if (check.isPresent()){
            FactureModel fact = check.get();
            this.factureRepository.delete(fact);
            return true;
        }
        throw new FactureNotFoundException(factureId);
    }
}
