package com.example.emtlab.service.impl;

import com.example.emtlab.model.Author;
import com.example.emtlab.model.Country;
import com.example.emtlab.model.dto.AuthorDto;
import com.example.emtlab.repository.AuthorRepository;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void AddAuthor(AuthorDto authorDto) {
        Optional<Country> c = countryRepository.findById(authorDto.getCountryId());
        if(!c.isPresent()){
            throw new RuntimeException("country not found");
        }
        Author a = new Author(authorDto.getName(), authorDto.getSurname(), c.get());
        authorRepository.save(a);
    }

    @Override
    public List<Author> GetAll() {
        return this.authorRepository.findAll();
    }
}
