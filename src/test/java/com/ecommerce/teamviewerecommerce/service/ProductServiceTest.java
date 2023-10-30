package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.APIException;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import com.ecommerce.teamviewerecommerce.payload.ProductResponse;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

public class ProductServiceTest {
    private ProductServiceImpl productService;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    private ProductDto productDto;

    @Before
    public void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        modelMapper = new ModelMapper();
        productService = new ProductServiceImpl(productRepository, modelMapper);
        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setImageUrl("assets/images/products/test.png");
        productDto.setName("TestName123");
        productDto.setUnitPrice(9.99);
        productDto.setUnitsInStock(100);
    }

    @Test
    public void testCreateProduct_Success() {
        // Given
        Product productEntity = modelMapper.map(productDto, Product.class);
        Mockito.when(productRepository.findByNameEquals(productDto.getName())).thenReturn(empty());
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(productEntity);

        // When
        ProductDto createdProduct = productService.createProduct(productDto);

        // Then
        assertNotNull(createdProduct);
        assertEquals(productDto.getName(), createdProduct.getName());

    }

    @Test
    public void testCreateProduct_DuplicateProduct() {
        // Given
        Mockito.when(productRepository.findByNameEquals(productDto.getName())).thenReturn(Optional.of(new Product()));

        // When and Then
        APIException exception = assertThrows(APIException.class, () -> productService.createProduct(productDto));
        assertEquals("Product already exists", exception.getMessage());
    }

    @Test
    public void testGetAllProducts_Success() {
        Product product1 = new Product();
        product1.setId(888L);
        product1.setImageUrl("assets/images/products/test.png");
        product1.setName("TestName123");
        product1.setDescription("Product description");
        product1.setUnitsInStock(100);
        product1.setUnitPrice(9.99);

        Product product2 = new Product();
        product2.setId(889L);
        product2.setImageUrl("assets/images/products/test.png");
        product2.setName("TestName123");
        product2.setDescription("Product description");
        product2.setUnitsInStock(100);
        product2.setUnitPrice(9.99);
        //Given
        int pageNo = 0;
        int pageSize = 2;
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        Page<Product> page = new PageImpl<>(products);
        Mockito.when(productRepository.findAll(Mockito.any(Pageable.class))).thenReturn(page);

        // When
        ProductResponse productResponse = productService.getAllProducts(pageNo, pageSize);

        // Then
        assertNotNull(productResponse);
        assertEquals(pageNo, productResponse.getPageNo());
        assertEquals(pageSize, productResponse.getPageSize());
        assertEquals(products.size(), productResponse.getContent().size());

    }


    @Test
    public void testGetProductById_Success() {
        Product product2 = new Product();
        product2.setId(888L);
        product2.setImageUrl("assets/images/products/test.png");
        product2.setName("TestName123");
        product2.setDescription("Product description");
        product2.setUnitsInStock(100);
        product2.setUnitPrice(9.99);
        // Given
        Long productId = 888L;

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product2));

        // When
        ProductDto productDto = productService.getProductById(productId);

        // Then
        assertNotNull(productDto);
        assertEquals(productId, productDto.getId());
        // Add more assertions as needed
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        // Given
        Long productId = 1L;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(productId));
        assertEquals("Product not found with id : '1'", exception.getMessage());
    }

    @Test
    public void testUpdateProductById_ProductNotFound() {
        // Given
        Long productId = 1L;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productService.updateProductById(productDto, productId));
        assertEquals("Product not found with id : '1'", exception.getMessage()); // Customize the error message as needed
    }

    @Test
    public void testDeleteProductById_Success() {
        // Given
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setImageUrl("assets/images/products/test.png");
        existingProduct.setName("TestName123");
        existingProduct.setDescription("Product description");
        existingProduct.setUnitsInStock(100);
        existingProduct.setUnitPrice(9.99);

        Long productId = 1L;
        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // When
        productService.deleteProductById(productId);

        // Then - Verify that the delete method was called
        Mockito.verify(productRepository).delete(existingProduct);
    }


        @Test
        public void testDeleteProductById_ProductNotFound() {
            // Given
            Product existingProduct = new Product();
            existingProduct.setId(1L);
            existingProduct.setImageUrl("assets/images/products/test.png");
            existingProduct.setName("TestName123");
            existingProduct.setDescription("Product description");
            existingProduct.setUnitsInStock(100);
            existingProduct.setUnitPrice(9.99);

            Long productId = 1L;
            Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

            // When and Then
            ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productService.deleteProductById(productId));
            assertEquals("Product not found with id : '1'", exception.getMessage());
        }
    }
