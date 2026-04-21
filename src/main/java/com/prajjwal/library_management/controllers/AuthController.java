package com.prajjwal.library_management.controllers;

import com.prajjwal.library_management.DTO.LoginRequestDTO;
import com.prajjwal.library_management.DTO.LoginResponseDTO;
import com.prajjwal.library_management.DTO.RegisterRequestDTO;
import com.prajjwal.library_management.entity.User;
import com.prajjwal.library_management.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
          return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }


}
