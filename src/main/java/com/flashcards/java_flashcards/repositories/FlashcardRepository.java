package com.flashcards.java_flashcards.repositories;

import com.flashcards.java_flashcards.model.entities.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {
    Flashcard findByName(String flashcardName);
}
