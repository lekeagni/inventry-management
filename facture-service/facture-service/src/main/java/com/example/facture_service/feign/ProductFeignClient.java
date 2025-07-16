package com.example.facture_service.feign;

import com.example.facture_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/product/{productId}")
    ProductDTO getProductById(@PathVariable int productId);
}
