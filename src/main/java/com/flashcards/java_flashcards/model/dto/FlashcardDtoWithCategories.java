package com.flashcards.java_flashcards.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class FlashcardDtoWithCategories {
    private Long id;
    private String name;
    private String content;
    private Set<CategoryDto> categoryDtos;
}
