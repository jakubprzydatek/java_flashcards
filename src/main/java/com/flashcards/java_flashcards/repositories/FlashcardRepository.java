package com.flashcards.java_flashcards.repositories;

import com.flashcards.java_flashcards.model.entities.Category;
import com.flashcards.java_flashcards.model.entities.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    Flashcard findByName(String flashcardName);

    @Override
    List<Flashcard> findAll();

    @Query("select f from Flashcard f join f.categories c where c.id = :category_id")
    List<Flashcard> findFlashcardsByCategory(@Param("category_id") Long categoryId);

}
