package com.flashcards.java_flashcards.controller;

import com.flashcards.java_flashcards.model.dto.FlashcardDto;
import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.services.FlashcardsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FlashcardRestController {

    @Autowired
    private FlashcardsService flashcardsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/flashcard")
    @ResponseBody
    public List<FlashcardDto> getFlashcards()
    {
        List<Flashcard> flashcardList = flashcardsService.getFlashcardsList();

        return flashcardList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/flashcard")
    public Flashcard addFlashcard(@RequestBody Flashcard flashcard)
    {
        return flashcardsService.addFlashcard(flashcard);
    }

    private FlashcardDto convertToDto(Flashcard flashcard)
    {
        return modelMapper.map(flashcard, FlashcardDto.class);
    }
}
