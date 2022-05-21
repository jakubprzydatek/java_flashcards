package com.flashcards.java_flashcards.repositories;

import com.flashcards.java_flashcards.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName);

    @Override
    List<Category> findAll();

    List<Category> findAllByFlashcardIdIn(List<Long> ids);
}
