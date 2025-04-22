package com.example.themisproductmanagment.mapper;

import com.example.themisproductmanagment.dto.ProductDto;
import com.example.themisproductmanagment.dto.UserDto;
import com.example.themisproductmanagment.entity.Product;
import com.example.themisproductmanagment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtoList(List<Product> productList);

    void updateProduct(ProductDto productDto , @MappingTarget Product product);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    void updateUser(UserDto userDto, @MappingTarget User user);



}
