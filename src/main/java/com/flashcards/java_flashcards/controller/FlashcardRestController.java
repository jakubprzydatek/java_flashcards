package com.flashcards.java_flashcards.controller;

import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.services.FlashcardsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlashcardRestController {

    private FlashcardsService flashcardsService;

    @GetMapping("/flashcard")
    public List<Flashcard> getFlashcards()
    {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @PostMapping("/flashcard")
    public Flashcard addFlashcard(@RequestBody Flashcard flashcard)
    {
        return flashcardsService.addFlashcard(flashcard);
    }
}
