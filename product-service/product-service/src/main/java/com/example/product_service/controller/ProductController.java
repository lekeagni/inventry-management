package com.example.product_service.controller;

import com.example.product_service.dto.ProductDTO;
import com.example.product_service.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "test products", description = "APIs REST test product and interactive documentation with swagger")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    @Operation(summary = "add product", description = "save product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "saved product successful"),
            @ApiResponse(responseCode = "400", description = "failed to create product"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct( dto));
    }

    @GetMapping()
    @Operation(summary = "get all products", description = "get all products in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product successful"),
            @ApiResponse(responseCode = "400", description = "failed to get products"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<List<ProductDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "get product by id", description = "get product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get product successful"),
            @ApiResponse(responseCode = "400", description = "product not found"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int productId){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getProductById(productId));
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "update product", description = "update product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "updated product successful"),
            @ApiResponse(responseCode = "400", description = "failed to update product"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<ProductDTO> update(@PathVariable int productId, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.update(productId, dto));
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "delete product", description = "delete product in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted product successful"),
            @ApiResponse(responseCode = "400", description = "failed to delete product"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean> delete( @PathVariable int productId){
        boolean exist = this.productService.deleteProduct(productId);
        if (exist){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

}
