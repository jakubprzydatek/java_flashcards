package com.flashcards.java_flashcards.services;

import com.flashcards.java_flashcards.model.entities.Category;
import com.flashcards.java_flashcards.repositories.CategoryRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class CategoriesService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category addCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }


}
