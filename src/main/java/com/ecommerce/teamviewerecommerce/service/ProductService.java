package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import com.ecommerce.teamviewerecommerce.payload.ProductResponse;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductResponse getAllProducts(int pageNo, int pageSize);

    ProductDto getProductById(Long id);

    ProductDto updateProductById(ProductDto productDto, Long id);

    void deleteProductById(Long id);
}
