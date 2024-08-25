package com.chatbotapp.chatbotapp.converters;

import com.chatbotapp.chatbotapp.dto.user.UserDTO;
import com.chatbotapp.chatbotapp.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getDateCreated(),
                user.getDateUpdated(),
                user.getName(),
                user.getLastName(),
                user.getAge(),
                user.getUsername()
        );
    }
}
