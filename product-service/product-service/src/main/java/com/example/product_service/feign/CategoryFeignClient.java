package com.example.product_service.feign;

import com.example.product_service.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "category-service")
public interface CategoryFeignClient {

    @GetMapping("/category/{categoryId}")
    CategoryDTO getCategoryById(@PathVariable int categoryId);
}
