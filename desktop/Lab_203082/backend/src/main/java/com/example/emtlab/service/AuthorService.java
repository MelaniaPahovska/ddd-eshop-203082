package com.example.emtlab.service;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    void AddAuthor(AuthorDto authorDto);
    List<Author> GetAll();
}
