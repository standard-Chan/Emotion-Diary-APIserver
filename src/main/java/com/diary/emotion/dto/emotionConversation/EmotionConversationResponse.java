package com.diary.emotion.dto.emotionConversation;

import com.diary.emotion.domain.EmotionConversation;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
public class EmotionConversationResponse {
    private final ArrayList<String> userConversation;
    private final ArrayList<String> assistantConversation;

    @Builder
    public EmotionConversationResponse(ArrayList<String> userConversation, ArrayList<String> assistantConversation) {
        this.userConversation = userConversation;
        this.assistantConversation = assistantConversation;
    }
}
