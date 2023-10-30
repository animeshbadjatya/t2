package com.ecommerce.teamviewerecommerce.service.impl;

import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import com.ecommerce.teamviewerecommerce.payload.ProductResponse;
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

import java.util.List;
import java.util.Optional;
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
       Optional<Product> existingProduct = productRepository.findByNameEquals(productDto.getName());
       if(existingProduct.isPresent())
           throw new APIException("ProductName already exists");

       Product newProduct = mapper.map(productDto, Product.class);
       Product savedProduct = productRepository.save(newProduct);
        productDto.setId(savedProduct.getId());

       return mapper.map(savedProduct, ProductDto.class);
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
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) throw new ResourceNotFoundException("Product", "id", id);

        System.out.println(product.toString());
        System.out.println(" productDto"+ productDto.toString());
        Product dbProduct  = product.get();
        Product updateProduct = mapper.map(productDto, Product.class);
        updateProduct.setId(dbProduct.getId());
        updateProduct.setDescription(dbProduct.getDescription());
        updateProduct.setName(dbProduct.getName());
        updateProduct.setUnitPrice(dbProduct.getUnitPrice());
        updateProduct.setUnitsInStock(dbProduct.getUnitsInStock());
        Product updatedProduct = productRepository.save(updateProduct);
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
