package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Product;

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


    //JUnit test for save product operation
    @DisplayName("JUnit test for save product operation")
    @Test
    public void givenProductObject_whenSave_thenReturnSavedProduct(){

        Product product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setActive(true);
        product.setName("TestName123");
        product.setUnitsInStock(100);


        Product savedProduct = productRepository.save(product);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getId()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for get all products operation")
    @Test
    public void givenProductList_whenFindAll_thenReturnProductList(){

        Product firstProduct = new Product();
        firstProduct.setId(888L);
        firstProduct.setImageUrl("assets/images/products/test.png");
        firstProduct.setActive(true);
        firstProduct.setName("TestName123");
        firstProduct.setUnitsInStock(100);

        Product secondProduct = new Product();
        secondProduct.setId(888L);
        secondProduct.setImageUrl("assets/images/products/test.png");
        secondProduct.setActive(true);
        secondProduct.setName("TestName123");
        secondProduct.setUnitsInStock(100);


        Product savedFirstProduct = productRepository.save(firstProduct);
        Product savedSecondProduct = productRepository.save(secondProduct);

        List<Product> productList = productRepository.findAll();

        assertThat(productList).isNotNull();
        assertThat(productList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for get product by ID operation")
    @Test
    public void givenProductObject_whenFindById_thenReturnProductObject(){

        Product product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setActive(true);
        product.setName("TestName123");
        product.setUnitsInStock(100);


        Product savedProduct = productRepository.save(product);

        Product productDb = productRepository.findById(savedProduct.getId()).get();

        assertThat(productDb).isNotNull();
    }

    @DisplayName("JUnit test for get product by Name operation")
    @Test
    public void givenProductObject_whenFindByName_thenReturnProductObject(){

        Product product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setActive(true);
        product.setName("TestName123");
        product.setUnitsInStock(100);


        Product savedProduct = productRepository.save(product);

        Product productDb = productRepository.findByNameEquals(savedProduct.getName()).get();

        assertThat(productDb.getName()).isEqualTo(product.getName());
    }

    @DisplayName("JUnit test for Update product operation")
    @Test
    public void givenProductObject_whenUpdated_thenReturnUpdatedProduct(){

        Product product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setActive(true);
        product.setName("TestName123");
        product.setUnitsInStock(100);


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

        Product product = new Product();
        product.setId(888L);
        product.setImageUrl("assets/images/products/test.png");
        product.setActive(true);
        product.setName("TestName123");
        product.setUnitsInStock(100);


        Product savedProduct = productRepository.save(product);
        productRepository.deleteById(product.getId());

        Optional<Product> productOptional = productRepository.findById(product.getId());

        assertThat(productOptional).isNotNull();
    }


}
