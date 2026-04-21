package com.prajjwal.library_management.controllers;

import com.prajjwal.library_management.DTO.RegisterRequestDTO;
import com.prajjwal.library_management.entity.User;
import com.prajjwal.library_management.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registeradminuser")
    public ResponseEntity<User> registerAdminUser(@RequestBody RegisterRequestDTO registerRequestDTO) {

        return ResponseEntity.ok(authenticationService.registerAdminUser(registerRequestDTO));
    }

}
