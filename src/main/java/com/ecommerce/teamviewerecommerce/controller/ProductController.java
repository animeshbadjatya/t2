package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.service.ProductService;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Products"
)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired // Can be removed
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @Operation(
            summary = "Create Product REST API",
            description = "This API created the Product in the Product table"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 created."
    )
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        System.out.println("In controller " + productDto.toString());
        return new ResponseEntity<>(productService.createPost(productDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return new ResponseEntity<>(productService.getAllProducts(pageNo, pageSize), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name ="id") long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updatePostById(@RequestBody ProductDto productDto, @PathVariable(name ="id") long id){
        ProductDto productResponse = productService.updateProductById(productDto,id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById( @PathVariable(name ="id") long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
