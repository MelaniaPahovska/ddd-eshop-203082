package com.example.emtlab.service;

import com.example.emtlab.model.Book;
import com.example.emtlab.model.Category;
import com.example.emtlab.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    void AddBook(BookDto bookDto);
    Page<Book> FindAllWithPagination(Pageable pageable);
    Book FindBookById(Long id);
    Book EditBook(Long bookId, BookDto bookDto);
    void DeleteBook(Long bookId);
    Book BorrowBook(Long bookId);
}
