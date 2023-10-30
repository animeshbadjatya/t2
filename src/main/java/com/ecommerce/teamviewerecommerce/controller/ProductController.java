package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.service.ProductService;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Products"
)
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired // Can be removed
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }


    @Operation(
            summary = "Create Product REST API",
            description = "This API created the Product in the Product table",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Http Status 201 created."
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
        System.out.println("In controller " + productDto.toString());
        return new ResponseEntity<>(productService.createProduct(productDto),HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get the list of Products REST API",
            description = "This API get the Products with Pagination",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns all the Products with Page Number starting from 0 and having Default Page Size of 10"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return new ResponseEntity<>(productService.getAllProducts(pageNo, pageSize), HttpStatus.OK);

    }

    @Operation(
            summary = "Get the Products with the specified ID",
            description = "This API get the Products with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns the Products with the specified ID"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name ="id") long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update the Products with the specified ID",
            description = "This API updates the Products with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns the updated Products with the specified ID"
                    )
            }
    )

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProductById( @Valid @RequestBody ProductDto productDto, @PathVariable(name ="id") long id){
        ProductDto productResponse = productService.updateProductById(productDto,id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes the Product with the specified ID",
            description = "This API deletes the Product with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns Success if the Product was deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "This API failed to find the Product to delete"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById( @PathVariable(name ="id") long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
