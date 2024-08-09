package com.maids.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.maids.library_management_system.model.Book;
import com.maids.library_management_system.service.BookService;

import jakarta.validation.Valid;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    public Book createBook( @Valid @RequestBody Book book) {
         
         return bookService.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@Valid @PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
