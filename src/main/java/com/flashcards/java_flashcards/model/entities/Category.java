package com.flashcards.java_flashcards.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String shortName;

    @OneToMany(mappedBy = "category")
    private Set<Flashcard> flashcard;
}
