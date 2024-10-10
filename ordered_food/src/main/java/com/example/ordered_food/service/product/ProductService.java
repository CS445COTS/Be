package com.example.ordered_food.service.product;

import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Product;
import com.example.ordered_food.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    Product createProduct(CreateProductRequest productRequest);

    String deleteProduct (Long productId) throws ProductException;

    Product updateProduct(Product req,Long id) throws ProductException;

    Product findProductById(Long id) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<Product> getAllProduct(String category,Integer minPrice,Integer maxPrice,String sort,
                                Integer pageNumber,Integer pageSize);

    List<Product> findAllProducts() throws  ProductException;

}
