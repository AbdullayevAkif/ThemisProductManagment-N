package com.example.themisproductmanagment.controller;

import com.example.themisproductmanagment.dto.ProductDto;
import com.example.themisproductmanagment.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

@GetMapping("/get-all-products")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }
@GetMapping("/get-user")
    public ProductDto getProductById(Long id) {
        return productService.getProduct(id);
    }
@PostMapping("/create-user")
    public ProductDto createProduct(ProductDto productDto) {
        return productService.createProduct(productDto);
    }

@PutMapping("/update-product")
    public String updateProduct(ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/delete-product")
    public String deleteProduct(Long id) {
        return productService.deleteProduct(id);
    }
}
