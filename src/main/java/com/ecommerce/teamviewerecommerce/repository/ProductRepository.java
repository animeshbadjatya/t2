package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
