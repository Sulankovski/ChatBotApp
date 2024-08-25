package com.chatbotapp.chatbotapp.repositories;

import com.chatbotapp.chatbotapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
