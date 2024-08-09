package com.maids.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
 
import org.springframework.stereotype.Service;

import com.maids.library_management_system.exception.ResourceNotFoundException;
 
import com.maids.library_management_system.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> 
        new ResourceNotFoundException("User not found"));
        
    }
}