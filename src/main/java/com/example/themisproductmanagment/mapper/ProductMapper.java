package com.example.themisproductmanagment.mapper;

import com.example.themisproductmanagment.dto.ProductDto;

import com.example.themisproductmanagment.entity.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    Product toProduct(ProductDto productDto);


    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> productList);


    void updateProduct(ProductDto productDto , @MappingTarget Product product);

}
