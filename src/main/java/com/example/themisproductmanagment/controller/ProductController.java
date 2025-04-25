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
@GetMapping("/get-product")
    public ProductDto getProductById(@RequestParam Long id) {
        return productService.getProduct(id);
    }
@PostMapping("/create-product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {

        return productService.createProduct(productDto);
    }

@PutMapping("/update-product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/delete-product")
    public String deleteProduct(@RequestParam Long id) {
        return productService.deleteProduct(id);
    }
}
