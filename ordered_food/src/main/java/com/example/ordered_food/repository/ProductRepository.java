package com.example.ordered_food.repository;


import com.example.ordered_food.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    @Query(value = "select p from Product p " +
            "where (:category IS NULL OR :category = '' OR p.category.name = :category) " +
            "and ((:minPrice IS NULL AND :maxPrice IS NULL) " +
            "OR (p.price BETWEEN :minPrice AND :maxPrice)) " +
            "order by " +
            "case when :sort = 'price_low' then p.price end DESC, " +
            "case when :sort = 'price_high' then p.price end ASC")

    List<Product> filterProducts(@Param("category") String category,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,

                                 @Param("sort") String sort);
}
