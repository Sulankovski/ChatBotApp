package com.chatbotapp.chatbotapp.controllers;

import com.chatbotapp.chatbotapp.dto.LoginRequestDTO;
import com.chatbotapp.chatbotapp.dto.RegisterRequestDTO;
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
    public String login(@RequestBody LoginRequestDTO loginRequest) {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.register(registerRequestDTO);
    }
}
