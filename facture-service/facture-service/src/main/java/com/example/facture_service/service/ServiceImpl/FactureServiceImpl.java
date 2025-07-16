package com.example.facture_service.service.ServiceImpl;

import com.example.facture_service.dto.FactureDTO;
import com.example.facture_service.dto.OrderDTO;
import com.example.facture_service.dto.ProductDTO;
import com.example.facture_service.exception.FactureNotFoundException;
import com.example.facture_service.exception.OrderNotFoundException;
import com.example.facture_service.feign.OrderFeignClient;
import com.example.facture_service.feign.ProductFeignClient;
import com.example.facture_service.mapper.FactureMapper;
import com.example.facture_service.model.FactureModel;
import com.example.facture_service.repository.FactureRepository;
import com.example.facture_service.service.FactureService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final FactureMapper factureMapper;
    private final OrderFeignClient orderFeignClient;
    private final ProductFeignClient productFeignClient;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper, OrderFeignClient orderFeignClient, ProductFeignClient productFeignClient) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
        this.orderFeignClient = orderFeignClient;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public FactureDTO createFacture(FactureDTO dto) {

        OrderDTO orderDTO;
        try {
            orderDTO = this.orderFeignClient.getOrderById(dto.getOrderId());
        }catch (FeignException .NotFound e){
             throw new OrderNotFoundException(dto.getOrderId());
        }

        ProductDTO productDTO = this.productFeignClient.getProductById(orderDTO.getProductId());

        Double totalAmount = productDTO.getPrice() * orderDTO.getQuantity();

        FactureModel factureModel = factureMapper.toEntity(dto);
        factureModel.setOrderId(orderDTO.getOrderId());
        factureModel.setPrice(totalAmount);
        factureModel.setDate(LocalDateTime.now());
        factureModel.setStatus("pending");

        return factureMapper.toDTO(this.factureRepository.save(factureModel));

    }

    @Override
    public List<FactureDTO> getAllFactures() {
        List<FactureModel> exist = this.factureRepository.findAll();
        return exist.stream().map(factureMapper::toDTO).toList();
    }

    @Override
    public FactureDTO getFactureById(int factureId) {
        FactureModel factureModel = this.factureRepository.findById(factureId)
                .orElseThrow(()->new FactureNotFoundException(factureId));
        return factureMapper.toDTO(factureModel);
    }

    @Override
    public FactureDTO updateFacture(int factureId, FactureDTO dto) {

        FactureModel existingFacture = this.factureRepository.findById(factureId)
                .orElseThrow(()->new FactureNotFoundException(factureId));

        OrderDTO orderDTO;
        try {
             orderDTO = this.orderFeignClient.getOrderById(dto.getOrderId());
        }catch (FeignException .NotFound e){
            throw new OrderNotFoundException(dto.getOrderId());
        }
        ProductDTO productDTO = this.productFeignClient.getProductById(orderDTO.getProductId());

        Double totalAmount = productDTO.getPrice() * orderDTO.getQuantity();

        existingFacture.setOrderId(dto.getOrderId());
        existingFacture.setPrice(totalAmount);
        existingFacture.setDate(LocalDateTime.now());
        existingFacture.setStatus("pending");

        return factureMapper.toDTO(this.factureRepository.save(existingFacture));
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
