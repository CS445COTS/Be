package com.example.ordered_food.service.product;


import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {


    List<Category> findAlCategories();

    Category findCategoryById(Long id) throws ProductException;
}
