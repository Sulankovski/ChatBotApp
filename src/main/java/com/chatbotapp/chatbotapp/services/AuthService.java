package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.config.JwtService;
import com.chatbotapp.chatbotapp.dto.RegisterRequestDTO;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public String login(String email, String password) {
        return authenticate(email, passwordEncoder.encode(password));
    }

    public String authenticate(String email, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetails userDetails = (UserDetails) userRepository.findByEmail(email);
        return jwtService.generateToken(userDetails);
    }

    public String register(RegisterRequestDTO registerRequest) {
        if (this.userRepository.findByEmail(registerRequest.getEmail()) != null) {
            throw new RuntimeException();
        }
        if (registerRequest.getPassword() != registerRequest.getPassword()) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setUsernameCustom("pavel.p");
        user.setName("Pavel");
        user.setEmail(registerRequest.getEmail());
        user.setLastName("Paunovski");
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setAge(12);

        user = userRepository.save(user);

        return this.authenticate(user.getEmail(), user.getPasswordHash());
    }
}
