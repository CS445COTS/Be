package com.example.ordered_food.service.product;


import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Category;
import com.example.ordered_food.model.Product;
import com.example.ordered_food.repository.CategoryRepository;
import com.example.ordered_food.repository.ProductRepository;
import com.example.ordered_food.request.CreateProductRequest;
import com.example.ordered_food.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImlp implements  ProductService{


    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;

    public ProductServiceImlp(ProductRepository productRepository, UserService userService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(CreateProductRequest productRequest) {
        Category category = categoryRepository.findByName(productRequest.getCategory());

        Product product = new Product();
        product.setTitle(productRequest.getTitle());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setQuantity(productRequest.getQuantity());
        product.setDiscountedPrice(product.getDiscountedPrice());
        product.setDiscountPercent(product.getDiscountPercent());
        product.setCategory(category);
        product.setCreateAt(LocalDateTime.now());
        Product saveProduct = productRepository.save(product);
        return saveProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
       Product product = findProductById(productId);
       productRepository.delete(product);
        return "Sản phẩm đã bị xóa";

    }

    @Override
    public Product updateProduct(Product req, Long id) throws ProductException {
        Product product = findProductById(req.getId());
        if(req.getQuantity() != 0){
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            return  opt.get();
        }
        throw  new ProductException("Product not found with id -"+id);
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, Integer minPrice, Integer maxPrice, String sort, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,sort);

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(),products.size());
        List<Product> pageContent = products.subList(startIndex,endIndex);

        Page<Product> filteredProducts = new PageImpl<>(pageContent,pageable,products.size());


        return filteredProducts;
    }

    @Override
    public List<Product> findAllProducts() throws ProductException {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()){
            throw  new ProductException("Danh sách sản phẩm không có gì");
        }else {
            return productList;
        }

    }
}
