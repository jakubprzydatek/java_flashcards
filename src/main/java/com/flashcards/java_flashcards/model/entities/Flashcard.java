package com.flashcards.java_flashcards.model.entities;

import javax.persistence.*;

@Entity
@Table(name="Flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="FLASHCARD_NAME", length=50, nullable=false, unique=false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
