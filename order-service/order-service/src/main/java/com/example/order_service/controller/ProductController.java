package com.example.order_service.controller;

import com.example.order_service.dto.ProductDTO;
import com.example.order_service.feign.ProductFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductFeignClient productFeignClient;

    public ProductController(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "get product by id", description = "get product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product successful"),
            @ApiResponse(responseCode = "400", description = "failed to get product"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int productId){
        return ResponseEntity.status(HttpStatus.OK).body(this.productFeignClient.getProductById(productId));
    }
}
