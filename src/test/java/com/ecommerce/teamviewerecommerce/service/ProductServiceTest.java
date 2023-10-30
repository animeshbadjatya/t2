//package com.ecommerce.teamviewerecommerce.service;
//
//import ch.qos.logback.core.net.SyslogOutputStream;
//import com.ecommerce.teamviewerecommerce.dto.ProductDto;
//import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
//import com.ecommerce.teamviewerecommerce.entity.Product;
//import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
//import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
//import com.ecommerce.teamviewerecommerce.service.impl.ProductServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import static org.hamcrest.Matchers.any;
//import static org.mockito.BDDMockito.given;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.awt.*;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.never;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    @Autowired
//    private ModelMapper mapper;
//
////    @BeforeEach
////    public void setup(){
////        productRepository = Mockito.mock(ProductRepository.class);
////        productService = new ProductServiceImpl(productRepository);
////    }
//
//    private Product product;
//    @BeforeEach
//    public void setup(){
//        product = new Product();
//        product.setId(888L);
//        product.setImageUrl("assets/images/products/test.png");
//        product.setName("TestName123");
//        product.setUnitPrice(9.99);
//        product.setUnitsInStock(100);
//    }
//
//    @DisplayName("JUnit test for save product method")
//    @Test
//    public void givenProductObject_whenSave_thenReturnSavedProduct(){
//
//        given(productRepository.findByNameEquals(product.getName()))
//                .willReturn(null);
//
//        given(productRepository.save(product)).willReturn(product);
//
//
//        Product savedProduct = productRepository.save(product);
//
//        assertThat(savedProduct).isNotNull();
//
//    }
////    @DisplayName("JUnit test for save product method which throws exception")
////    @Test
////    public void givenExistingProductObject_whenSave_thenThrowsException(){
////
////        given(productRepository.findByNameEquals(product.getName())).willReturn(Optional.empty());
////        given(productRepository.save(product)).willReturn(product);
////
////        System.out.println(productRepository );
////        System.out.println(" **** "+ productRepository);
////       ProductDto productDto = mapToDto(product);
////        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, ()-> productService.createProduct(productDto));
////
////        verify(productRepository, never()).save(any(Product.class));
////    }
//
////    @DisplayName("JUnit test for getting all product method")
////    @Test
////    public void givenProductObjects_whenGetAllProduct_thenReturnAllSavedProduct(){
////
////        Product product1 = new Product();
////        product1.setId(888L);
////        product1.setImageUrl("assets/images/products/test.png");
////        product1.setName("TestName123");
////        product1.setUnitPrice(9.99);
////        product1.setUnitsInStock(100);
////
////        given(productRepository.findAll()).willReturn(List.of(product, product1));
////
////        ProductResponse listOfProducts  = productService.getAllProducts(0,2);
////
////
////        assertThat(listOfProducts).isNotNull();
////        assertThat(listOfProducts.getContent().size()).isEqualTo(2);
////
////    }
//
//    private ProductDto mapToDto(Product product){
//        return mapper.map(product, ProductDto.class);
//
//    }
//    private Product mapToEntity(ProductDto productDto){
//        return mapper.map(productDto, Product.class);
//
//    }
//}
