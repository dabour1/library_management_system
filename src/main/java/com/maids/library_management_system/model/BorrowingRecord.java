package com.maids.library_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
 
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@Data
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    // @NotBlank(message = "book_id is required")
    @NotNull(message = "book_id cannot be null")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    // @NotBlank(message = "patron_id is required")
    @NotNull(message = "patron_id cannot be null")
    private Patron patron;

    @NotNull(message = "borrowingDate cannot be null")
    @PastOrPresent(message = "borrowingDate must be in the past or present")
    private LocalDate borrowingDate;

     
    @PastOrPresent(message = "returnDate must be in the past or present")
    private LocalDate returnDate;

    
}
