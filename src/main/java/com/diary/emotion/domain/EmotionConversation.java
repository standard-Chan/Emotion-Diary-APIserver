package com.diary.emotion.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class EmotionConversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String date;

    @Column(name = "user_conversation")
    private ArrayList<String> userConversation;

    @Column(name = "assistant_conversation")
    private ArrayList<String> assistantConversation;


    public EmotionConversation(String userEmail, String date) {
        this.userEmail = userEmail;
        this.date = date;
        this.userConversation = new ArrayList<String>();
        this.assistantConversation = new ArrayList<>();
    }

    public EmotionConversation addUserConversation(String message) {
        this.userConversation.add(message);
        return this;
    }

    public EmotionConversation addAssistantConversation(String message) {
        this.assistantConversation.add(message);
        return this;
    }

}
