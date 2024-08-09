package com.maids.library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.maids.library_management_system.model.Patron;
import com.maids.library_management_system.service.PatronService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    private PatronService patronService;

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.findAll();
    }

    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Long id) {
        return patronService.findById(id);
    }

    @PostMapping
    public Patron createPatron( @Valid @RequestBody Patron patron) {
        return patronService.save(patron);
    }

    @PutMapping("/{id}")
    public Patron updatePatron(@Valid  @PathVariable Long id, @RequestBody Patron patron) {
        return patronService.update(id, patron);
    }

    @DeleteMapping("/{id}")
    public void deletePatron(@PathVariable Long id) {
        patronService.delete(id);
    }
}
