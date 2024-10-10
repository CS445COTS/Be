package com.example.ordered_food.repository;


import com.example.ordered_food.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {



    @Query(value = "select  p from  Product p " +
            "where (p.category.name = :category OR  :category='') and ((:minPrice is null and :maxPrice is null) " +
            "or (p.price BETWEEN  :minPrice and :maxPrice)) " +
            " order by  " +
            "case  when  :sort = 'price_low' then p.price END  desc ," +
            "case when  :sort = 'price_high' then p.price END  asc ")
    List<Product> filterProducts(@Param("category") String category,
                                 @Param("minPrice") Integer minPrice,
                                 @Param("maxPrice") Integer maxPrice,

                                 @Param("sort") String sort);
}
