package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.dto.user.UserCreationDTO;
import com.chatbotapp.chatbotapp.dto.user.UserUpdateDTO;
import com.chatbotapp.chatbotapp.exceptions.UserWithIdNotFoundException;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserWithIdNotFoundException(userId.toString()));
    }

    public User createUser(UserCreationDTO userCreationDTO) {
        User newUser = new User();
        newUser.setDateCreated(OffsetDateTime.now());
        newUser.setName(userCreationDTO.name());
        newUser.setLastName(userCreationDTO.lastName());
        newUser.setAge(userCreationDTO.age());
        newUser.setUsernameCustom(userCreationDTO.userName());

        return userRepository.save(newUser);
    }

    public User updateUser(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = findById(userId);
        user.setUsernameCustom(userUpdateDTO.userName());

        return userRepository.save(user);
    }
}
