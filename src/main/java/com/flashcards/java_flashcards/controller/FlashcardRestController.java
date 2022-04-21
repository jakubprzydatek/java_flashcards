package com.flashcards.java_flashcards.controller;

import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.services.FlashcardsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlashcardRestController {

    private FlashcardsService flashcardsService;

    @PostMapping("/api/flashcard")
    public Flashcard addFlashcard(@RequestBody Flashcard flashcard)
    {
        return flashcardsService.addFlashcard(flashcard);
    }
}
