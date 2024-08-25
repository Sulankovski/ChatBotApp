package com.chatbotapp.chatbotapp.controllers;

import com.chatbotapp.chatbotapp.converters.UserConverter;
import com.chatbotapp.chatbotapp.dto.user.UserCreationDTO;
import com.chatbotapp.chatbotapp.dto.user.UserDTO;
import com.chatbotapp.chatbotapp.dto.user.UserUpdateDTO;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/rest/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/{userId}")
    public UserDTO findById(@PathVariable Long userId){
        return userConverter.toUserDTO(userService.findById(userId));
    }

    @PostMapping("/create-user")
    @Transactional(rollbackFor = Exception.class)
    public UserDTO createUser(@RequestBody UserCreationDTO userCreationDTO){
        return userConverter.toUserDTO(userService.createUser(userCreationDTO));
    }

    @PutMapping("/update-user/{userId}")
    @Transactional(rollbackFor = Exception.class)
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UserUpdateDTO userUpdateDTO){
        return userConverter.toUserDTO(userService.updateUser(userId, userUpdateDTO));
    }
}
