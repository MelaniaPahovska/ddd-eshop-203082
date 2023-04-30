package com.example.emtlab.config;

import com.example.emtlab.model.Category;
import com.example.emtlab.model.dto.AuthorDto;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.service.AuthorService;
import com.example.emtlab.service.BookService;
import com.example.emtlab.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;

    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }


    private Category randomizeEventType(int i) {
        if (i % 3 == 0) return Category.BIOGRAPHY;
        else if (i % 3 == 1) return Category.FANTASY;
        return Category.CLASSICS;
    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            this.countryService.AddCountry("Country" + i, "Continent" + i);
            this.authorService.AddAuthor(new AuthorDto("Author" + i, "Surname", this.countryService.GetAllCountries().get((i - 1) % 5).getId()));
        }

        for (int i = 1; i < 11; i++) {
            this.bookService.AddBook(new BookDto("Book name" + i, this.randomizeEventType(i), i + 1, this.authorService.GetAll().get((i - 1) % 5).getId()));
        }
    }
}
