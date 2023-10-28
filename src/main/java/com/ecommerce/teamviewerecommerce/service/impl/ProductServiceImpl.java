package com.ecommerce.teamviewerecommerce.service.impl;

import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.APIException;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ModelMapper mapper;
    private ProductRepository productRepository;

    @Autowired // We can omit this from Spring 3 onwards if the class has only once constructor
    public ProductServiceImpl(ProductRepository productRepository,  ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        System.out.println("Here in create Product" + productDto.getName());
       List<Product> productNameList = productRepository.findByNameEquals(productDto.getName());
       if(productNameList.isEmpty()) {

           // Convert DTO to entity
           System.out.println("Here in PostService Impl" + productDto.toString());
           Product product = mapper.map(productDto, Product.class);

           Product newProduct = productRepository.save(product);

           return mapper.map(newProduct, ProductDto.class);
       }else{
               throw new APIException("Product", "Name", productDto.getName());
           }
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize) {

        //Create Pageable instance
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Product> products = productRepository.findAll(pageable);

        // Get content from Page object
        List<Product> listOfProducts = products.getContent();

        List<ProductDto> content = listOfProducts.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());
        return productResponse;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(product);
    }

    @Override
    public ProductDto updateProductById(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "id", id);
        });

          Product  product1 = mapper.map(productDto, Product.class);


        Product updatedProduct = productRepository.save(product1);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Product", "id", id);
        });
        productRepository.delete(product);
    }

    // convert Entity to DTO
    private ProductDto mapToDTO(Product product){
         ProductDto productDto = mapper.map(product, ProductDto.class);
        return productDto;
    }
}
