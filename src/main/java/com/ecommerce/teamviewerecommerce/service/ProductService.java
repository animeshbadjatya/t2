package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductDto createPost(ProductDto productDto);

    ProductResponse getAllProducts(int pageNo, int pageSize);

    ProductDto getProductById(Long id);

    ProductDto updateProductById(ProductDto productDto, Long id);

    void deleteProductById(Long id);
}
