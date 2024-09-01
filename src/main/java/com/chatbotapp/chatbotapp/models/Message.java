package com.chatbotapp.chatbotapp.models;

import com.chatbotapp.chatbotapp.common.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messages", schema = "public")
public class Message extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "chat_room")
    private ChatRoom chatRoom;

    @Column(name = "content")
    private String content;
}
