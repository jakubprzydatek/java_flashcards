package com.flashcards.java_flashcards.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FlashcardDto {
    private Long id;
    private String name;
    private String content;
}
