package com.example.emtlab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Integer availableCopies;
    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(Long id, String name, Category category, Integer availableCopies, Author author) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
    }

}
