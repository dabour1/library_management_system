package com.maids.library_management_system.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.maids.library_management_system.exception.ResourceNotFoundException;
import com.maids.library_management_system.model.Patron;

import com.maids.library_management_system.repository.PatronRepository;

import jakarta.transaction.Transactional;


@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    @Cacheable(value = "patrons")
    public List<Patron> findAll() {
     List<Patron> patrons= patronRepository.findAll();
     if (patrons.isEmpty()) {
            throw new ResourceNotFoundException("No patrons found");
        }
     return patrons;

    }
    @Cacheable(value = "patrons", key = "#id")
    public Patron findById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron not found"));
    }
    @Transactional
    @CachePut(value = "patrons", key = "#patron.id")
    public Patron save(Patron patron) {
        Optional<Patron> existingPatron = patronRepository.findByEmail(patron.getEmail());
        if (existingPatron.isPresent()) {
            throw new IllegalArgumentException("Patron with email " + patron.getEmail() + " already exists");
        }
        patron.setId(null);
        return patronRepository.save(patron);
    }
    @Transactional
    @CachePut(value = "patrons", key = "#id")
    public Patron update(Long id, Patron updatedPatron) {
        Patron patron = findById(id);
        Optional<Patron> existingPatron = patronRepository.findByEmail(updatedPatron.getEmail());
        
        if (existingPatron.isPresent() &&!existingPatron.get().getId().equals(id)) {
            throw new IllegalArgumentException("Patron with email " + patron.getEmail() + " already exists");
        }
        patron.setName(updatedPatron.getName());
        patron.setEmail(updatedPatron.getEmail());
        patron.setPhone(updatedPatron.getPhone());
        return patronRepository.save(patron);
        
    }
    @Transactional
    @CacheEvict(value = "patrons", key = "#id")
    public void delete(Long id) {
        patronRepository.deleteById(id);
    }
}
