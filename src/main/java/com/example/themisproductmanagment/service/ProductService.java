package com.example.themisproductmanagment.service;

import com.example.themisproductmanagment.dto.ProductDto;


import java.util.List;

public interface ProductService {
    ProductDto getProduct(Long id);
    List<ProductDto> getProducts();
    ProductDto createProduct(ProductDto productDto);
    String updateProduct(ProductDto productDto);
    String deleteProduct(Long id );

}
