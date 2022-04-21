package com.flashcards.java_flashcards.services;

import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.repositories.FlashcardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class FlashcardsService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    public Flashcard addFlashcard(Flashcard flashcard){
        return flashcardRepository.save(flashcard);
    }
}
