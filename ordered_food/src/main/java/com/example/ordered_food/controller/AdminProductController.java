package com.example.ordered_food.controller;


import com.example.ordered_food.model.Product;
import com.example.ordered_food.request.CreateProductRequest;
import com.example.ordered_food.response.ApiResponse;
import com.example.ordered_food.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){

        Product product = productService.createProduct(req);
        return  new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public  ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
        for (CreateProductRequest product :req){
            productService.createProduct(product);
        }
        ApiResponse response = new ApiResponse();
        response.setMessage("product created multiple successfully");
        response.setStatus(true);
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
