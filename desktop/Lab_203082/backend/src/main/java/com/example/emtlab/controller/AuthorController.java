package com.example.emtlab.controller;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Book;
import com.example.emtlab.model.dto.AuthorDto;
import com.example.emtlab.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public List<Author> findAll() {
        return this.authorService.GetAll();
    }

    @PostMapping()
    public void saveAuthor(@RequestBody AuthorDto authorDto) {
        this.authorService.AddAuthor(authorDto);
    }
}
