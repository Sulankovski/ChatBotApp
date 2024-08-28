package com.chatbotapp.chatbotapp.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}
