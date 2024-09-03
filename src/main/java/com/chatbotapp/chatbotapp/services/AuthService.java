package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.config.JwtService;
import com.chatbotapp.chatbotapp.converters.UserConverter;
import com.chatbotapp.chatbotapp.dto.authorization.AuthorizationResponseDTO;
import com.chatbotapp.chatbotapp.dto.authorization.RegisterRequestDTO;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;


    public AuthorizationResponseDTO login(String email, String password) {
        return authenticate(email, password);
    }

    public AuthorizationResponseDTO authenticate(String email, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetails userDetails = (UserDetails) userRepository.findByEmail(email);
        String token = jwtService.generateToken(userDetails);
        User user = userRepository.findByEmail(email);

        return new AuthorizationResponseDTO(token, userConverter.toUserDTO(user));
    }

    public AuthorizationResponseDTO register(RegisterRequestDTO registerRequest) {
        if (this.userRepository.findByEmail(registerRequest.email()) != null) {
            throw new RuntimeException();
        }
        if (registerRequest.password() != registerRequest.password()) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setDateCreated(OffsetDateTime.now());
        user.setUsernameCustom(registerRequest.userName());
        user.setName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setLastName(registerRequest.lastName());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password()));
        user.setAge(registerRequest.age());

        user = userRepository.save(user);

        return this.authenticate(user.getEmail(), registerRequest.password());
    }
}
