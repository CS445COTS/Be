package com.example.ordered_food.controller;


import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Product;
import com.example.ordered_food.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class ProductController {


    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryhandler(@RequestParam String category,
                                                             @RequestParam Integer minPrice,
                                                             @RequestParam Integer maxPrice,
                                                             @RequestParam String sort,
                                                                      @RequestParam Integer pageNumber,
                                                                      @RequestParam Integer pageSize){
        Page<Product> productResponse = productService.getAllProduct(category,minPrice,maxPrice,sort,pageNumber,pageSize);

        return  new ResponseEntity<>(productResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/id/{productId}")
    public  ResponseEntity<Product> findProductByHandler(@PathVariable Long productId) throws ProductException {
        Product product = productService.findProductById(productId);
        return  new ResponseEntity<>(product,HttpStatus.ACCEPTED);

    }
}
