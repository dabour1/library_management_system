package com.maids.library_management_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 

import com.maids.library_management_system.model.AuthenticationResponse;
import com.maids.library_management_system.model.User;
import com.maids.library_management_system.repository.UserRepository;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
     @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    
    public AuthenticationResponse register(User request  ){
        Optional<User>   userExistind = userRepository.findByUsername(request.getUsername());
        if (userExistind.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(request);
        final String jwtToken = jwtService.generateToken(user.getUsername());
        return new AuthenticationResponse(jwtToken);

    }

    public AuthenticationResponse authenticat(User request) throws Exception  {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                     request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtService.generateToken(userDetails.getUsername());

        return new AuthenticationResponse(jwtToken);
    }



}
