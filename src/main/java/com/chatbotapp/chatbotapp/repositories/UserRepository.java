package com.chatbotapp.chatbotapp.repositories;

import com.chatbotapp.chatbotapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
