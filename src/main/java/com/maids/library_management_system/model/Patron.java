package com.maids.library_management_system.model;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Entity
@Data
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotBlank(message = "email is required")
    @NotNull(message ="email cannot be null")
    @Email(message = "Email should be valid")
     @Column(unique = true)

    private String email; 

    @NotBlank(message = "phone is required")
    @NotNull(message = "phone cannot be null")
    @Size(min = 10, max = 14, message = "phone must be between 10 and 14 characters")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number must be valid and can include digits, spaces, parentheses, and hyphens.")
    private String phone;
}
