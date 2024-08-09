package com.maids.library_management_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maids.library_management_system.exception.ResourceNotFoundException;
import com.maids.library_management_system.model.Book;
import com.maids.library_management_system.model.BorrowingRecord;
import com.maids.library_management_system.model.Patron;
import com.maids.library_management_system.repository.BookRepository;

import com.maids.library_management_system.repository.PatronRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.maids.library_management_system.repository.BorrowingRecordRepository;

import java.time.LocalDate;
import java.util.Optional;
 


@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
    @Valid Book book = bookService.findById(bookId);
     @Valid Patron patron = patronService.findById(patronId);
    Optional< BorrowingRecord> existingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
    if (existingRecord.isPresent()&&existingRecord.get().getReturnDate()==null) {   
        throw new IllegalArgumentException("Book already borrowed by this patron");
    }
    BorrowingRecord record = new BorrowingRecord();
    record.setBook(book);
    record.setPatron(patron);
    record.setBorrowingDate(LocalDate.now());

    return borrowingRecordRepository.save(record);
    }
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found"));
        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }
}

