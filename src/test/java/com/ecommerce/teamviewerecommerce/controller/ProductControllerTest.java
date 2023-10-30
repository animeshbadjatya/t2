package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.APIException;
import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import com.ecommerce.teamviewerecommerce.payload.ProductResponse;
import com.ecommerce.teamviewerecommerce.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductControllerTest {
    private ProductController productController;
    @Autowired
    private ProductServiceImpl productService;

    private ProductDto productDto;
    @Before
    public void setup() {
        productService = Mockito.mock(ProductServiceImpl.class);
        productController = new ProductController(productService);
        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setImageUrl("assets/images/products/test.png");
        productDto.setName("TestName123");
        productDto.setDescription("Product description");
        productDto.setUnitsInStock(100);
        productDto.setUnitPrice(9.99);
    }

    @DisplayName("JUnit test for save product operation")
    @Test
    public void givenProductObject_whenSave_thenReturnSaved() {
        Mockito.when(productService.createProduct(Mockito.any())).thenReturn(productDto);
        ResponseEntity<ProductDto> result = productController.createProduct(productDto);
        Mockito.verify(productService).createProduct(productDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(productDto.getName(), result.getBody().getName());
        assertEquals(productDto.getUnitsInStock(), result.getBody().getUnitsInStock());
        assertEquals(productDto.getUnitPrice(), result.getBody().getUnitPrice());
        assertEquals(productDto.getDescription(), result.getBody().getDescription());
    }

    @DisplayName("JUnit test for get product when ID already exists operation")
    @Test
    public void givenProductObject_whenSave_thenIdAlreadyExists() {
        Mockito.when(productService.createProduct(productDto))
                .thenThrow(new APIException("Product already exists"));
        APIException exception = assertThrows(APIException.class, () -> productController.createProduct(productDto));
        assertEquals("Product already exists", exception.getMessage());
    }

    @DisplayName("JUnit test for get all products operation")
    @Test
    public void givenProductList_whenFindAll_thenReturnAllProducts() {
        int pageNo = 0;
        int pageSize = 10;
        ProductResponse productResponse = new ProductResponse();
        Mockito.when(productService.getAllProducts(pageNo, pageSize)).thenReturn(productResponse);
        ResponseEntity<ProductResponse> result = productController.getAllProducts(pageNo, pageSize);
        Mockito.verify(productService).getAllProducts(pageNo, pageSize);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(productResponse, result.getBody());
    }

    @DisplayName("JUnit test for get product by ID operation")
    @Test
    public void givenProductID_whenFindById_thenReturnProductObject(){
        long productId = 1L;
        Mockito.when(productService.getProductById(productId)).thenReturn(productDto);
        ResponseEntity<ProductDto> result = productController.getProductById(productId);
        Mockito.verify(productService).getProductById(productId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(productDto, result.getBody());
    }

    @DisplayName("JUnit test for Update product operation")
    @Test
    public void givenProductId_whenUpdated_thenReturnUpdatedProduct() {
        long productId = 1L;
        Mockito.when(productService.updateProductById(Mockito.any(), Mockito.eq(productId))).thenReturn(productDto);

        ResponseEntity<ProductDto> result = productController.updateProductById(productDto, productId);

        Mockito.verify(productService).updateProductById(productDto, productId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(productDto, result.getBody());
    }

    @Test
    public void givenProductObject_whenDeleted_thenReturnSuccess() {
        long productId = 1L;

        ResponseEntity<String> result = productController.deleteProductById(productId);

        Mockito.verify(productService).deleteProductById(productId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("\"Success\"", result.getBody());
    }


}
