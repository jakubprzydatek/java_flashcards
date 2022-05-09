package com.flashcards.java_flashcards.services;

import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.repositories.FlashcardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class FlashcardsService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    public List<Flashcard> getFlashcardsList(){
        return flashcardRepository.findAll();
    }

    public Flashcard addFlashcard(Flashcard flashcard){
        return flashcardRepository.save(flashcard);
    }
}
