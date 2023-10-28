package com.ecommerce.teamviewerecommerce.service.impl;

import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public ProductDto createPost(ProductDto productDto) {
        // Convert DTO to entity
        System.out.println( "Here in PostService Impl" + productDto.toString());
        Product product = new Product();
     //   product.setId(product.getId());
        product.setName(productDto.getName());
        product.setSku(productDto.getSku());
        product.setDescription(productDto.getDescription());
        product.setUnitPrice(productDto.getUnitPrice());
        product.setUnitsInStock(productDto.getUnitsInStock());
        product.setImageUrl(productDto.getImageUrl());
        product.setActive(productDto.isActive());

        Product newProduct = productRepository.save(product);

        // convert Entity to DTO
        ProductDto productResponse = new ProductDto();
        productResponse.setId(newProduct.getId());
        productResponse.setName(newProduct.getName());
        productResponse.setSku(newProduct.getSku());
        productResponse.setDescription(newProduct.getDescription());
        productResponse.setUnitPrice(newProduct.getUnitPrice());
        productResponse.setUnitsInStock(newProduct.getUnitsInStock());
        productResponse.setImageUrl(newProduct.getImageUrl());
        productResponse.setDateCreated(newProduct.getDateCreated());
        productResponse.setLastUpdated(newProduct.getLastUpdated());


        return  productResponse;
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
//        product.setName(productDto.getName());
//        product.setSku(productDto.getSku());
//        product.setDescription(productDto.getDescription());
//        product.setUnitPrice(productDto.getUnitPrice());
//        product.setUnitsInStock(productDto.getUnitsInStock());
//        product.setImageUrl(productDto.getImageUrl());
//        product.setActive(productDto.isActive());


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
//        ProductDto productResponse = new ProductDto();
//        productResponse.setId(product.getId());
//        productResponse.setName(product.getName());
//        productResponse.setSku(product.getSku());
//        productResponse.setDescription(product.getDescription());
//        productResponse.setUnitPrice(product.getUnitPrice());
//        productResponse.setUnitsInStock(product.getUnitsInStock());
//        productResponse.setImageUrl(product.getImageUrl());
//        productResponse.setDateCreated(product.getDateCreated());
//        productResponse.setLastUpdated(product.getLastUpdated());
        return productDto;
    }
}
