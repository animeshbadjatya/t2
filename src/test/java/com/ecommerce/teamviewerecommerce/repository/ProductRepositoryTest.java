package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private Product product;
    @BeforeEach
    public void setup(){
        product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setName("TestName123");
        product.setDescription("Product description");
        product.setUnitsInStock(100);
        product.setUnitPrice(9.99);
    }

    //JUnit test for save product operation
    @DisplayName("JUnit test for save product operation")
    @Test
    public void givenProductObject_whenSave_thenReturnSavedProduct(){

        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for get all products operation")
    @Test
    public void givenProductList_whenFindAll_thenReturnProductList(){


        Product secondProduct = new Product();
        secondProduct.setId(880L);
        secondProduct.setImageUrl("assets/images/products/test.png");
        secondProduct.setName("TestingName");
        secondProduct.setUnitsInStock(100);


        Product savedFirstProduct = productRepository.save(product);
        Product savedSecondProduct = productRepository.save(secondProduct);

        List<Product> productList = productRepository.findAll();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for get product by ID operation")
    @Test
    public void givenProductObject_whenFindById_thenReturnProductObject(){

        Product savedProduct = productRepository.save(product);

        Product productDb = productRepository.findById(savedProduct.getId()).get();
        assertThat(productDb).isNotNull();
    }

    @DisplayName("JUnit test for get product by Name operation")
    @Test
    public void givenProductObject_whenFindByName_thenReturnProductObject(){

        Product savedProduct = productRepository.save(product);

        Product productDb = productRepository.findByNameEquals(savedProduct.getName()).get();

        assertThat(productDb.getName()).isEqualTo(product.getName());
    }

    @DisplayName("JUnit test for Update product operation")
    @Test
    public void givenProductObject_whenUpdated_thenReturnUpdatedProduct(){


        Product savedProduct = productRepository.save(product);

        Product updateProduct = productRepository.findById(savedProduct.getId()).get();
        updateProduct.setUnitsInStock(21);
        updateProduct.setDescription("This is test");

        Product savedUpdatedProduct = productRepository.save(updateProduct);

        assertThat(savedUpdatedProduct.getUnitsInStock()).isEqualTo(21);
        assertThat(savedProduct.getDescription()).isEqualTo("This is test");
    }

    @DisplayName("JUnit test for Delete product operation")
    @Test
    public void givenProductObject_whenDeleted_thenRemoveProduct(){

        Product savedProduct = productRepository.save(product);
        productRepository.deleteById(product.getId());

        Optional<Product> productOptional = productRepository.findById(product.getId());

        assertThat(productOptional).isEmpty();
    }


}
