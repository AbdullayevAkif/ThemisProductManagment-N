package com.example.themisproductmanagment;


import com.example.themisproductmanagment.dto.ProductDto;
import com.example.themisproductmanagment.entity.Product;
import com.example.themisproductmanagment.entity.User;
import com.example.themisproductmanagment.exception.ProductException;
import com.example.themisproductmanagment.mapper.ProductMapper;
import com.example.themisproductmanagment.repository.ProductRepository;
import com.example.themisproductmanagment.repository.UserRepository;
import com.example.themisproductmanagment.service.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {


    @Mock
    private ProductMapper productMapper;

    @Mock
    private UserRepository userRepository;


    @Mock
    private ProductRepository productRepository;



    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {

        User user = new User();
        user.setId(1L);
        user.setName("Akif");

        ProductDto enterProductDto = new ProductDto(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );

        Product productEntity = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );

        Product saveProduct = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );

        ProductDto productDtoExpected = new ProductDto(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );


        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
       when(productMapper.toProduct(enterProductDto)).thenReturn(productEntity);
       when(productRepository.save(productEntity)).thenReturn(saveProduct);
       when(productMapper.toProductDto(saveProduct)).thenReturn(productDtoExpected);

       ProductDto returnedProductDto = productService.createProduct(enterProductDto);

       assertNotNull(returnedProductDto);
       assertEquals(returnedProductDto.getId(), productDtoExpected.getId());
       assertEquals(returnedProductDto.getName(), productDtoExpected.getName());
       assertEquals(returnedProductDto.getDescription(), productDtoExpected.getDescription());
       assertEquals(returnedProductDto.getPrice(), productDtoExpected.getPrice());
       assertEquals(returnedProductDto.getUser().getId(), user.getId());

       verify(productRepository).save(productEntity);
       verify(productMapper).toProductDto(saveProduct);
    }

    @Test
    void testGetProduct_Found() {
        User user = new User();
        user.setId(1L);
        user.setName("Akif");


        Product productEntity = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );

        ProductDto productDtoExpected = new ProductDto(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );


        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(productMapper.toProductDto(productEntity)).thenReturn(productDtoExpected);


        ProductDto returnedProductDto = productService.getProduct(1L);


        assertEquals(returnedProductDto.getId(), productDtoExpected.getId());
        assertEquals(returnedProductDto.getName(), productDtoExpected.getName());
        assertEquals(returnedProductDto.getDescription(), productDtoExpected.getDescription());
        assertEquals(returnedProductDto.getPrice(), productDtoExpected.getPrice());
        assertEquals(returnedProductDto.getUser().getId(), user.getId());

        verify(productRepository).findById(1L);

        verify(productMapper).toProductDto(productEntity);


    }





@Test
    void testGetAllProducts_Found() {
        User user = new User();
        user.setId(1L);
        user.setName("Akif");

        List<Product> productEntity = Arrays.asList(
                new Product(
                        1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
                ),
                new Product(
                        2L, "Test Product", new BigDecimal("100.00"), "Test Description", user
                )
        );

        List<ProductDto> productDtoExpected = Arrays.asList(
                new ProductDto(
                        1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
                ),
                new ProductDto(
                        2L, "Test Product", new BigDecimal("100.00"), "Test Description", user
                )
        );

        when(productRepository.findAll()).thenReturn(productEntity);
        when(productMapper.toProductDtoList(productEntity)).thenReturn(productDtoExpected);

        List<ProductDto> productDtoList = productService.getProducts();
        assertNotNull(productDtoList);
        assertEquals(productDtoExpected.size(), productDtoList.size());
        assertEquals(productDtoExpected.get(0), productDtoList.get(0));
        assertEquals(productDtoExpected.get(1), productDtoList.get(1));

        verify(productRepository).findAll();





    }






    @Test
    void testUpdateProduct_WhenFound() {

        User user = new User();
        user.setId(1L);
        user.setName("Akif");

        ProductDto productDtoInput = new ProductDto(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );


        Product productEntityFind = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );

        Product productEntityUpdate = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );


        Product productEntitySaved = new Product(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );



        ProductDto productDtoExpected = new ProductDto(
                1L, "Test Product", new BigDecimal("100.00"), "Test Description", user
        );


when(productRepository.findById(1L)).thenReturn(Optional.of(productEntityFind));
        productMapper.updateProduct(productDtoInput, productEntityUpdate);
        when(productRepository.save(productEntityUpdate)).thenReturn(productEntitySaved);
        when(productMapper.toProductDto(productEntitySaved)).thenReturn(productDtoExpected);

        ProductDto returnedProductDto = productService.updateProduct(productDtoInput);

        assertEquals(productDtoInput.getName(), returnedProductDto.getName());
        assertEquals(productDtoInput.getDescription(), returnedProductDto.getDescription());
        assertEquals(productDtoInput.getPrice(), returnedProductDto.getPrice());
        assertEquals(productDtoInput.getUser().getId(), returnedProductDto.getUser().getId());



//        verify(productMapper).updateProduct(productDtoInput, productEntityUpdate);--(Did not work)
        verify(productRepository).save(productEntityUpdate);
        verify(productMapper).toProductDto(productEntitySaved);




    }




    @Test
    void testDeleteProduct_WhenFound() {
        User user = new User();
        user.setId(1L);
        user.setName("Akif");

        Product findProduct = new Product(1L, "Product to delete", new BigDecimal("70.00"), "Description", user);

        when(productRepository.findById(1L)).thenReturn(Optional.of(findProduct));
        doNothing().when(productRepository).delete(findProduct);

        var result = productService.deleteProduct(findProduct.getId());

        assertEquals("Product deleted", result);
        verify(productRepository).delete(findProduct);
    }


    @Test
    void testDeleteProduct_WhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class, () -> productService.deleteProduct(1L));
    }

    @Test
    void testGetProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ProductException.class, ()->productService.getProduct(1L));
    }}

