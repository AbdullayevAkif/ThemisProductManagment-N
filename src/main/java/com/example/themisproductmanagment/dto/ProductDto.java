package com.example.themisproductmanagment.dto;

import com.example.themisproductmanagment.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults( level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    BigDecimal price;
    String description;
    User user;
}
