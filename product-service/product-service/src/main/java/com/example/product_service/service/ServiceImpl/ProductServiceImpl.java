package com.example.product_service.service.ServiceImpl;

import com.example.product_service.dto.CategoryDTO;
import com.example.product_service.dto.ProductDTO;
import com.example.product_service.dto.UserDTO;
import com.example.product_service.exception.CategoryNotFoundException;
import com.example.product_service.exception.ProductAlreadyExistException;
import com.example.product_service.exception.ProductNotFoundException;
import com.example.product_service.exception.UserNotFoundException;
import com.example.product_service.feign.CategoryFeignClient;
import com.example.product_service.feign.UserFeignClient;
import com.example.product_service.mapper.ProductMapper;
import com.example.product_service.model.ProductModel;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryFeignClient categoryFeignClient;
    private final UserFeignClient userFeignClient;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryFeignClient categoryFeignClient, UserFeignClient userFeignClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryFeignClient = categoryFeignClient;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public ProductDTO createProduct( ProductDTO dto) {
        Optional<ProductModel> checkProduct = this.productRepository.findByName(dto.getName());
        if (checkProduct.isPresent()){
            throw new ProductAlreadyExistException(dto.getName());
        }

            CategoryDTO categoryDTO = this.categoryFeignClient.getCategoryById(dto.getCategoryId());
        if (categoryDTO == null){
            throw  new CategoryNotFoundException(dto.getCategoryId());
        }
        UserDTO userDTO = this.userFeignClient.getUserById(dto.getUserId());
        if (userDTO == null){
            throw new UserNotFoundException(dto.getUserId());
        }

        ProductModel pro = productMapper.toEntity(dto);
        pro.setName(dto.getName());
        pro.setDescription(dto.getDescription());
        pro.setPrice(dto.getPrice());
        pro.setQuantity(dto.getQuantity());
        pro.setCategoryId(dto.getCategoryId());
        pro.setUserId(dto.getUserId());

        return productMapper.toDTO(this.productRepository.save(pro));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductModel> get = this.productRepository.findAll();
        return get.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int productId) {
        ProductModel productModel = this.productRepository.findById(productId)
                .orElseThrow(()->new CategoryNotFoundException(productId));
        return productMapper.toDTO(productModel);
    }

    @Override
    public ProductDTO update(int productId, ProductDTO dto) {

        // vérification
        ProductModel productModel = this.productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));

        Optional<ProductModel> checkProduct = this.productRepository.findByName(dto.getName());
        if (checkProduct.isPresent()){
            throw new ProductAlreadyExistException(dto.getName());
        }
        CategoryDTO categoryDTO = this.categoryFeignClient.getCategoryById(dto.getCategoryId());
        if (categoryDTO == null){
            throw new CategoryNotFoundException(dto.getCategoryId());
        }

        // mis a jour des champs
        productModel.setName(dto.getName());
        productModel.setDescription(dto.getDescription());
        productModel.setPrice(dto.getPrice());
        productModel.setQuantity(dto.getQuantity());
        productModel.setCategoryId(dto.getCategoryId());

        return productMapper.toDTO(this.productRepository.save(productModel));
    }

    @Override
    public Boolean deleteProduct(int productId) {
        Optional<ProductModel> productModel = this.productRepository.findById(productId);
        if (productModel.isPresent()){
            ProductModel pro = productModel.get();
            this.productRepository.delete(pro);
            return true;
        }
        return false;
    }
}
