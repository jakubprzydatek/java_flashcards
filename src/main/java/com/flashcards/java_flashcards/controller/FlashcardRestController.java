package com.flashcards.java_flashcards.controller;

import com.flashcards.java_flashcards.model.dto.CategoryDto;
import com.flashcards.java_flashcards.model.dto.FlashcardDto;
import com.flashcards.java_flashcards.model.dto.FlashcardDtoWithCategories;
import com.flashcards.java_flashcards.model.entities.Category;
import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.services.CategoriesService;
import com.flashcards.java_flashcards.services.FlashcardsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class FlashcardRestController {

    @Autowired
    private FlashcardsService flashcardsService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/flashcard")
    @ResponseBody
    public List<FlashcardDto> getFlashcards()
    {
        List<Flashcard> flashcardList = flashcardsService.getFlashcardsList();

        return flashcardList.stream()
                .map(this::convertFlashcardToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/flashcard")
    @ResponseBody
    public FlashcardDto addFlashcard(@RequestBody Flashcard flashcard)
    {
        //Flashcard flashcard = convertFlashcardToEntity(flashcardDto);
        //Flashcard createdFlashcard = flashcardsService.addFlashcard(flashcard);

        return convertFlashcardToDto(flashcardsService.addFlashcard(flashcard));
    }

    @GetMapping("/category")
    @ResponseBody
    public List<CategoryDto> getCategories()
    {
        List<Category> categoryList = categoriesService.getAllCategories();

        return categoryList.stream()
                .map(this::convertCategoryToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{id}/flashcards")
    public List<FlashcardDto> getFlashcardListByCategory(@PathVariable long id)
    {
        List<Flashcard> flashcardList = flashcardsService.findFlashcardsByCategory(id);

        return flashcardList.stream()
                .map(this::convertFlashcardToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/flashcard")
    public FlashcardDto editFlashcard(@RequestBody Flashcard flashcard)
    {
        return convertFlashcardToDto(flashcardsService.editFlashcard(flashcard));
    }

    @GetMapping("/flashcards/categories")
    public List<FlashcardDtoWithCategories> getFlashcardsWithCategories()
    {
        List<Flashcard> flashcardList = flashcardsService.getFlashcardsWithCategories();
        return flashcardList.stream()
                .map(this::convertFlashcardToFlashcardDtoWithCategories)
                .collect(Collectors.toList());
    }

    @PostMapping("/category")
    @ResponseBody
    public Category addCategory(@RequestBody Category category)
    {
        return categoriesService.addCategory(category);
    }

    private FlashcardDto convertFlashcardToDto(Flashcard flashcard)
    {
        return modelMapper.map(flashcard, FlashcardDto.class);
    }

    private Flashcard convertFlashcardToEntity(FlashcardDto flashcardDto)
    {
        return modelMapper.map(flashcardDto, Flashcard.class);
    }

    private CategoryDto convertCategoryToDto(Category category)
    {
        return modelMapper.map(category, CategoryDto.class);
    }

    private FlashcardDtoWithCategories convertFlashcardToFlashcardDtoWithCategories(Flashcard flashcard)
    {
        return mapToFlashcardDtoWithCategories(flashcard);
    }

    private FlashcardDtoWithCategories mapToFlashcardDtoWithCategories(Flashcard flashcard)
    {
        return FlashcardDtoWithCategories.builder()
                .id(flashcard.getId())
                .name(flashcard.getName())
                .content(flashcard.getContent())
                .categoryDtos(flashcard
                        .getCategories()
                        .stream()
                        .map(this::convertCategoryToDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    private Category convertCategoryToEntity(CategoryDto categoryDto)
    {
        return modelMapper.map(categoryDto, Category.class);
    }
}
