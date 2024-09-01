package com.chatbotapp.chatbotapp.dto.authorization;

import com.chatbotapp.chatbotapp.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

public record AuthorizationResponseDTO(
        String token,
        UserDTO userDTO
) {
}
