package com.chatbotapp.chatbotapp.models;

import com.chatbotapp.chatbotapp.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat_room", schema = "public")
public class ChatRoom extends BaseEntity {
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
