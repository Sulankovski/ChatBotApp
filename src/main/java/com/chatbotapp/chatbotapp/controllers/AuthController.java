package com.chatbotapp.chatbotapp.controllers;

import com.chatbotapp.chatbotapp.dto.authorization.AuthorizationResponseDTO;
import com.chatbotapp.chatbotapp.dto.authorization.LoginRequestDTO;
import com.chatbotapp.chatbotapp.dto.authorization.RegisterRequestDTO;
import com.chatbotapp.chatbotapp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthorizationResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest.email(), loginRequest.password());
    }

    @PostMapping("/register")
    public AuthorizationResponseDTO register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }
}
