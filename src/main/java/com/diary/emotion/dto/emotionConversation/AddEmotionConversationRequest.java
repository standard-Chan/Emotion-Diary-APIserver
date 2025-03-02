package com.diary.emotion.dto.emotionConversation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddEmotionConversationRequest {
    private String userEmail;
    private String date;
    private String userMessage;
    private String assistantMessage;


}
