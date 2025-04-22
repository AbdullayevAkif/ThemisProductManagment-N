//package com.example.themisproductmanagment;
//
//
//import com.example.themisproductmanagment.dto.ProductDto;
//import com.example.themisproductmanagment.entity.Product;
//import com.example.themisproductmanagment.exception.ProductException;
//import com.example.themisproductmanagment.mapper.EntityMapper;
//import com.example.themisproductmanagment.repository.ProductRepository;
//import com.example.themisproductmanagment.service.serviceImpl.ProductServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceImplTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private EntityMapper entityMapper;
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    @Test
//    void testCreateProduct() {
//        ProductDto productDto = new ProductDto(
//                1L, "Test Product", "Test Description", new BigDecimal("100.00")
//        );
//
//        Product product = new Product(
//                1L, "Test Product", new BigDecimal("100.00"), "Test Description"
//        );
//
////        when(entityMapper.toProduct(productDto)).thenReturn(product);
////        when(productRepository.save(product)).thenReturn(product);
////        when(entityMapper.toProductDto(product)).thenReturn(productDto);
////
////        ProductDto result = productService.createProduct(productDto);
////
////        assertNotNull(result);
////        assertEquals(productDto.getId(), result.getId());
////        assertEquals(productDto.getName(), result.getName());
////        assertEquals(productDto.getDescription(), result.getDescription());
////        assertEquals(productDto.getPrice(), result.getPrice());
////        verify(productRepository).save(product);
//
//        when(entityMapper.toProduct(productDto)).thenReturn(product);
//        when(productRepository.save(product)).thenReturn(product);
//        when(entityMapper.toProductDto(product)).thenReturn(productDto);
//
//        ProductDto resultProduct = productService.createProduct(productDto);
//
//        assertNotNull(resultProduct);
//        assertEquals(productDto.getId(), resultProduct.getId());
//        assertEquals(productDto.getName(), resultProduct.getName());
//        assertEquals(productDto.getDescription(), resultProduct.getDescription());
//        assertEquals(productDto.getPrice(), resultProduct.getPrice());
//        verify(productRepository).save(product);
//
//    }
//
//    @Test
//    void testGetProduct_WhenFound() {
//        Product product = new Product(
//                1L, "Test Product", new BigDecimal("150.00"), "Test Description"
//        );
//
//        ProductDto productDto = new ProductDto(
//                1L, "Test Product", "Test Description", new BigDecimal("150.00")
//        );
//
////        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
////        when(entityMapper.toProductDto(product)).thenReturn(productDto);
////
////        ProductDto result = productService.getProduct(1L);
////
////        assertNotNull(result);
////        assertEquals(productDto.getId(), result.getId());
////        assertEquals(productDto.getName(), result.getName());
////        assertEquals(productDto.getDescription(), result.getDescription());
////        assertEquals(productDto.getPrice(), result.getPrice());
//
//
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//        when(entityMapper.toProductDto(product)).thenReturn(productDto);
//        ProductDto resultProduct = productService.getProduct(product.getId());
//        assertNotNull(resultProduct);
//        assertEquals(productDto.getId(), resultProduct.getId());
//        assertEquals(productDto.getName(), resultProduct.getName());
//        assertEquals(productDto.getDescription(), resultProduct.getDescription());
//        assertEquals(productDto.getPrice(), resultProduct.getPrice());
////        verify(productRepository).findById(product.getId());
//    }
//
//    @Test
//    void testGetProduct_WhenNotFound() {
//        when(productRepository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(ProductException.class, ()->productService.getProduct(1L));
//    }
//
//    @Test
//    void testGetProducts() {
//        List<Product> products = Arrays.asList(
//                new Product(1L, "Product 1", new BigDecimal("50.00"), "Desc 1"),
//                new Product(2L, "Product 2", new BigDecimal("60.00"), "Desc 2")
//        );
//
//        List<ProductDto> productDtos = Arrays.asList(
//                new ProductDto(1L, "Product 1", "Desc 1", new BigDecimal("50.00")),
//                new ProductDto(2L, "Product 2", "Desc 2", new BigDecimal("60.00"))
//        );
//
//       when(productRepository.findAll()).thenReturn(products);
//       when(entityMapper.toProductDtoList(products)).thenReturn(productDtos);
//       List<ProductDto> resultProducts = productService.getProducts();
//       assertNotNull(resultProducts);
//       assertEquals(productDtos.size(), resultProducts.size());
//       assertEquals(productDtos, resultProducts);
//    }
//
//    @Test
//    void testUpdateProduct_WhenFound() {
//        ProductDto productDto = new ProductDto(
//                1L, "Updated Product", "Updated Description", new BigDecimal("200.00")
//        );
//
//        Product existingProduct = new Product(
//                1L, "Old Product", new BigDecimal("100.00"), "Old Description"
//        );
//
//
//        when(productRepository.findById(productDto.getId())).thenReturn(Optional.of(existingProduct));
//        doNothing().when(entityMapper).updateProduct(productDto, existingProduct);
//        when(productRepository.save(existingProduct)).thenReturn(existingProduct);
//        when(entityMapper.toProductDto(existingProduct)).thenReturn(productDto);
//
//        String updateProductResult = productService.updateProduct(productDto);
//
//        assertNotNull(updateProductResult);
//        assertEquals("Product updated", updateProductResult);
//        verify(productRepository).save(existingProduct);
//
//    }
//
//    @Test
//    void testUpdateProduct_WhenNotFound() {
//        ProductDto productDto = new ProductDto(
//                1L, "Updated Product", "Updated Description", new BigDecimal("200.00")
//        );
//
//        when(productRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ProductException.class, () -> productService.updateProduct(productDto));
//    }
//
//    @Test
//    void testDeleteProduct_WhenFound() {
//        Product product = new Product(
//                1L, "Product to delete", new BigDecimal("70.00"), "Description"
//        );
//
//       when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//       doNothing().when(productRepository).delete(product);
//
//       String deleteProductResult = productService.deleteProduct(product.getId());
//       assertNotNull(deleteProductResult);
//       assertEquals("Product deleted", deleteProductResult);
//       verify(productRepository).delete(product);
//
//
//    }
//
//    @Test
//    void testDeleteProduct_WhenNotFound() {
//        when(productRepository.findById(1L)).thenReturn(Optional.empty());
//        assertThrows(ProductException.class, () -> productService.deleteProduct(1L));
//    }
//}
