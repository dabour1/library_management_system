package com.maids.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maids.library_management_system.model.AuthenticationResponse;
import com.maids.library_management_system.model.User;
import com.maids.library_management_system.service.AuthenticationService;
 

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(authenticationService.register(user)) ;
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticat(user)) ;
    }
}
