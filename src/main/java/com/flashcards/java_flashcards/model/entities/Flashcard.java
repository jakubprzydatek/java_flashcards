package com.flashcards.java_flashcards.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FLASHCARD_NAME", nullable = false)
    private String name;

    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
