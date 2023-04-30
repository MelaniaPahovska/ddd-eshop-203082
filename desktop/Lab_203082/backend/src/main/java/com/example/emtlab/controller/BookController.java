package com.example.emtlab.controller;

import com.example.emtlab.model.Book;
import com.example.emtlab.model.Category;
import com.example.emtlab.model.dto.BookDto;
import com.example.emtlab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping()
    public Page<Book> findAll(Pageable pageable) {
        return this.bookService.FindAllWithPagination(pageable);
    }

    @GetMapping("{id}")
    public Book find(@PathVariable Long id) {
        return this.bookService.FindBookById(id);
    }

    @GetMapping("/categories")
    public List<Category> findCat() {
        return Arrays.stream(Category.values()).toList();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editById(@PathVariable Long id,
                                         @RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(this.bookService.EditBook(id, bookDto));
    }

    @PostMapping("/add")
    public void save(@RequestBody BookDto bookDto) {
        this.bookService.AddBook(bookDto);
    }

    @PutMapping("/borrow/{id}")
    public ResponseEntity<Book> borrow(@PathVariable Long id) {
        return  ResponseEntity.ok().body(this.bookService.BorrowBook(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.DeleteBook(id);
        return ResponseEntity.ok().build();
    }
}
