package com.example.emtlab.model.dto;

import com.example.emtlab.model.Category;
import lombok.Data;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Integer availableCopies;
    private Long authorId;

    public BookDto() {
    }

    public BookDto(String name, Category category, Integer availableCopies, Long authorId) {
        this.name = name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.authorId = authorId;
    }
}
