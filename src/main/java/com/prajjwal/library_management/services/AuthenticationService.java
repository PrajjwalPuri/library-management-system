package com.prajjwal.library_management.services;

import com.prajjwal.library_management.DTO.LoginRequestDTO;
import com.prajjwal.library_management.DTO.LoginResponseDTO;
import com.prajjwal.library_management.DTO.RegisterRequestDTO;
import com.prajjwal.library_management.JWT.JwtService;
import com.prajjwal.library_management.entity.User;
import com.prajjwal.library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    // Method to register the normal user :-->
    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exist");
        }
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    // Method to register the admin user :-->
    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exist");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }


    // method to login user :--->

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );

        User user = userRepository.findByUsername(loginRequestDTO.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getUsername(), user.getId());

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }
}
