package com.example.product_service.service;

import com.example.product_service.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO dto);

    public List<ProductDTO> getAllProducts();

    public ProductDTO getProductById(int productId);

    public ProductDTO update(int productId, ProductDTO dto);

    public Boolean deleteProduct(int productId);
}
