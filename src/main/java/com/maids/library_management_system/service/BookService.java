package com.maids.library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.maids.library_management_system.exception.ResourceNotFoundException;
import com.maids.library_management_system.model.Book;
import com.maids.library_management_system.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Cacheable(value = "books")
    public List<Book> findAll() {
        
        List<Book> books= bookRepository.findAll();
        if(books.isEmpty()){
            throw new ResourceNotFoundException("No books found");
        }
        return books;
    }
    @Cacheable(value = "books", key = "#id")
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Transactional
    @CachePut(value = "books", key = "#book.id")
    public Book save(Book book) {
        Optional<Book> existingBook = getBookIfEexisting(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new IllegalArgumentException("Book with Isbn " + book.getIsbn() + " already exists");
        }
        book.setId(null);
        return bookRepository.save(book);
    }
    @CachePut(value = "books", key = "#id")
    @Transactional
    public Book update(Long id, Book updatedBook) {
        Book book = findById(id);
        Optional<Book> existingBook = getBookIfEexisting(updatedBook.getIsbn());

        if (existingBook.isPresent() &&!existingBook.get().getId().equals(id)) {
            throw new IllegalArgumentException("Book with Isbn" + updatedBook.getIsbn() + " already exists");
        }
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }
    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
    public Optional<Book> getBookIfEexisting(String Isbn){
        return  bookRepository.findByIsbn( Isbn);
    }
}
