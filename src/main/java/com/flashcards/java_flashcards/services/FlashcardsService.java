package com.flashcards.java_flashcards.services;

import com.flashcards.java_flashcards.model.entities.Category;
import com.flashcards.java_flashcards.model.entities.Flashcard;
import com.flashcards.java_flashcards.repositories.CategoryRepository;
import com.flashcards.java_flashcards.repositories.FlashcardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class FlashcardsService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Flashcard> getFlashcardsList(){
        return flashcardRepository.findAll();
    }

    public Optional<Flashcard> getFlashcardById(Long id)
    {
        return flashcardRepository.findById(id);
    }

    public Flashcard addFlashcard(Flashcard flashcard){
        Set<Long> categoriesIds = flashcard.getCategories().stream().map(Category::getId).collect(Collectors.toSet());
        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(categoriesIds));

        Flashcard flashcardToSave = Flashcard.builder()
                .id(flashcard.getId())
                .name(flashcard.getName())
                .content(flashcard.getContent())
                .categories(categories)
                .build();
        return flashcardRepository.save(flashcardToSave);
    }

    public List<Flashcard> findFlashcardsByCategory(Long categoryId)
    {
        return flashcardRepository.findFlashcardsByCategory(categoryId);
    }

    public List<Flashcard> getFlashcardsWithCategories()
    {
        List<Flashcard> allFlashcards = flashcardRepository.findAll();
        List<Long> flashcardsIds = allFlashcards.stream()
                .map(Flashcard::getId)
                .collect(Collectors.toList());

        List<Category> categories = categoryRepository.findAllByFlashcardIdIn(flashcardsIds);

        allFlashcards.forEach(flashcard -> flashcard.setCategories(extractCategories(categories, flashcard.getId())));

        return allFlashcards;
    }

    @Transactional
    public Flashcard editFlashcard(Flashcard flashcard)
    {
        Flashcard editedFlashcard = flashcardRepository.getById(flashcard.getId());
        editedFlashcard.setCategories(flashcard.getCategories());
        editedFlashcard.setName(flashcard.getName());
        editedFlashcard.setContent(flashcard.getContent());

        return editedFlashcard;
    }


    private Set<Category> extractCategories(List<Category> categories, Long flashcardId)
    {
        return categories.stream()
                .filter(category -> containsFlashcardId(category.getFlashcard(), flashcardId))
                .collect(Collectors.toSet());
    }

    private boolean containsFlashcardId(Set<Flashcard> flashcards, Long flashcardId)
    {
        List<Long> flashcardsIds = flashcards.stream()
                .map(Flashcard::getId)
                .collect(Collectors.toList());

        return flashcardsIds.contains(flashcardId);
    }

}
