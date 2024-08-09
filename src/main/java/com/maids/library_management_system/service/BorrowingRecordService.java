package com.maids.library_management_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maids.library_management_system.exception.ResourceNotFoundException;
import com.maids.library_management_system.model.Book;
import com.maids.library_management_system.model.BorrowingRecord;
import com.maids.library_management_system.model.Patron;
import com.maids.library_management_system.repository.BookRepository;

import com.maids.library_management_system.repository.PatronRepository;

import com.maids.library_management_system.repository.BorrowingRecordRepository;

import java.time.LocalDate;
 


@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowingDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }

    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing record not found"));
        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }
}

