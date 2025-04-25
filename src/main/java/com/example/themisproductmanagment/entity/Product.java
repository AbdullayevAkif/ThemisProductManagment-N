package com.example.themisproductmanagment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@FieldDefaults(level = AccessLevel.MODULE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    BigDecimal price;

    String description;


    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    User user;

}
