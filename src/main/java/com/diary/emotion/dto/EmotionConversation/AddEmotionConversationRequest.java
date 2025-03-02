package com.diary.emotion.dto.EmotionConversation;


import com.diary.emotion.domain.EmotionConversation;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Getter
@Setter
public class AddEmotionConversationRequest {
    private String userEmail;
    private String date;
    private String userMessage;
    private String assistantMessage;


}
