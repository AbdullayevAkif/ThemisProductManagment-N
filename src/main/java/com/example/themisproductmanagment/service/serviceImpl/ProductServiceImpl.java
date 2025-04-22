package com.example.themisproductmanagment.service.serviceImpl;


import com.example.themisproductmanagment.dto.ProductDto;
import com.example.themisproductmanagment.entity.Product;
import com.example.themisproductmanagment.exception.ProductException;
import com.example.themisproductmanagment.mapper.EntityMapper;
import com.example.themisproductmanagment.repository.ProductRepository;
import com.example.themisproductmanagment.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EntityMapper entityMapper;
    private final static String PRODUCT_CACHE = "products";

    public ProductServiceImpl(ProductRepository productRepository, EntityMapper entityMapper) {
        this.productRepository = productRepository;
        this.entityMapper = entityMapper;

    }

    @CachePut( value = PRODUCT_CACHE , key = "#result.getId()")
    public ProductDto createProduct(ProductDto productDto) {
        Product product = entityMapper.toProduct(productDto);
        return entityMapper.toProductDto(productRepository.save(product));
    }

    public ProductDto getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(()-> new ProductException("Product not found"));

        return entityMapper.toProductDto(product);
    }

    @Cacheable(value = PRODUCT_CACHE , key = "#result.getId()")
    public List<ProductDto> getProducts() {
        return entityMapper.toProductDtoList(productRepository.findAll());
    }


    @CachePut( value = PRODUCT_CACHE , key = "#result.getId()")
        public  String updateProduct(ProductDto productDto) {
        
        Product product = productRepository.findById(productDto.getId()).orElseThrow(()-> new ProductException("Product not found"));
        
         entityMapper.updateProduct(productDto,product);
         
       entityMapper.toProductDto( productRepository.save(product));
         
         
         return "Product updated";

    }


@CacheEvict(value = PRODUCT_CACHE , key = "#id")
    public    String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductException("Product not found"));
        productRepository.delete(product);
        return "Product deleted";
    }


}
