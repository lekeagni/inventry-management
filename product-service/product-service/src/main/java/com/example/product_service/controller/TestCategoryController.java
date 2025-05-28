package com.example.product_service.controller;

import com.example.product_service.dto.CategoryDTO;
import com.example.product_service.feign.CategoryFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@Tag(name = "category test", description = "APIs REST test with swagger and APIs REST interactive documentation ")
public class TestCategoryController {

    private final CategoryFeignClient categoryFeignClient;

    public TestCategoryController(CategoryFeignClient categoryFeignClient) {
        this.categoryFeignClient = categoryFeignClient;
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "get category", description = "get the category in the database by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get category successful"),
            @ApiResponse(responseCode = "400", description = "failed to create category"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryFeignClient.getCategoryById(categoryId));
    }
}
