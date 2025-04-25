package com.example.themisproductmanagment.service.serviceImpl;


import com.example.themisproductmanagment.dto.ProductDto;
import com.example.themisproductmanagment.entity.Product;
import com.example.themisproductmanagment.entity.User;
import com.example.themisproductmanagment.exception.ProductException;
import com.example.themisproductmanagment.mapper.ProductMapper;
import com.example.themisproductmanagment.repository.ProductRepository;
import com.example.themisproductmanagment.repository.UserRepository;
import com.example.themisproductmanagment.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final static String PRODUCT_CACHE = "products";
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;

        this.userRepository = userRepository;
    }

    @CachePut( value = PRODUCT_CACHE , key = "#result.getId()")
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        Long id= productDto.getUser().getId();
        User user = userRepository.findById(id).orElseThrow(()->new ProductException("User Not Found"));
        product.setUser(user);
        return productMapper.toProductDto(productRepository.save(product));
    }

    public ProductDto getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(()-> new ProductException("Product not found"));

        return productMapper.toProductDto(product);
    }

    @Cacheable(value = PRODUCT_CACHE , key = "#result.getId()")
    public List<ProductDto> getProducts() {
        return productMapper.toProductDtoList(productRepository.findAll());
    }


    @CachePut( value = PRODUCT_CACHE , key = "#result.getId()")
        public  ProductDto updateProduct(ProductDto productDto) {
        
        Product product = productRepository.findById(productDto.getId()).orElseThrow(()-> new ProductException("Product not found"));
        
         productMapper.updateProduct(productDto,product);
         
     ProductDto productDtoSave =   productMapper.toProductDto( productRepository.save(product));
     return productDtoSave;
         
         


    }


@CacheEvict(value = PRODUCT_CACHE , key = "#id")
    public    String deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductException("Product not found"));
        productRepository.delete(product);
        return "Product deleted";
    }


}
