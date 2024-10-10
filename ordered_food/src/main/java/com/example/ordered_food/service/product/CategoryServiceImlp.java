package com.example.ordered_food.service.product;


import com.example.ordered_food.exception.ProductException;
import com.example.ordered_food.model.Category;
import com.example.ordered_food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImlp  implements  CategoryService{


    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAlCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) throws ProductException {
        Optional<Category> category = categoryRepository.findById(id);

        if(category.isPresent()){
            return  category.get();
        }
         throw new ProductException("categories not found :"+id);
    }
}
