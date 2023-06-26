package com.leomouda.chatop.service;

import com.leomouda.chatop.dto.AuthRequestDTO;
import com.leomouda.chatop.dto.AuthResponseDTO;
import com.leomouda.chatop.dto.RegisterRequest;
import com.leomouda.chatop.dto.UserResponseDTO;
import com.leomouda.chatop.models.User;
import com.leomouda.chatop.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserService userService;

    public AuthResponseDTO register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();
        userService.createNewUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder()
                .bearerToken(jwtToken)
                .build();
    }

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        System.out.println("/// " + request.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder()
                .bearerToken(jwtToken)
                .build();
    }

    public UserResponseDTO getCurrentUser(@NonNull HttpServletRequest request) throws ServletException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException();
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwt);
        if (userEmail != null) {
            User user = userService.getUserByEmail(userEmail);
            if (jwtService.isTokenValid(jwt, userEmail)) {
                return userService.mapUserToDTO(user);
            }
        }
        throw new ServletException();
    }
}
