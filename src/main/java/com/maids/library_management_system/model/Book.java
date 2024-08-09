package com.maids.library_management_system.model;

 
 

 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
 
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;
    @NotBlank(message = "Author is mandatory")
    private String author;
    
    @Min(value = 1000, message = "Publication year should be valid")
    private int publicationYear;
    
    @NotBlank(message = "ISBN is mandatory")
    @Size(min = 13, max = 13, message = "ISBN should be 13 characters")
     @Column(unique = true)
    private String isbn;

    
    
     
}
