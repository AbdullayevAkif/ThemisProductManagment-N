package com.example.themisproductmanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ThemisProductManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThemisProductManagementApplication.class, args);
    }

}
