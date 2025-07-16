package com.example.facture_service.controller;

import com.example.facture_service.dto.ProductDTO;
import com.example.facture_service.feign.ProductFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductTestController {

    private final ProductFeignClient productFeignClient;

    public ProductTestController(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @Operation(summary = "get product by id", description = "get the product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product successful"),
            @ApiResponse(responseCode = "400", description = "failed to get product"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productFeignClient.getProductById(productId));
    }
}
