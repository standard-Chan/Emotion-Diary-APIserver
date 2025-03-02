package com.diary.emotion.controller;

import com.diary.emotion.domain.EmotionConversation;
import com.diary.emotion.dto.EmotionConversation.AddEmotionConversationRequest;
import com.diary.emotion.repository.EmotionConversationRepository;
import com.diary.emotion.service.EmotionConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emotionConversation")
public class EmotionConversationApiController {

    private final EmotionConversationService emotionConversationService;


    @PostMapping("/user")
    public ResponseEntity<EmotionConversation> updateConversation(@RequestBody AddEmotionConversationRequest request) {
        String userEmail = request.getUserEmail();
        String date = request.getDate();
        String userMessage = request.getUserMessage();
        String assistantMessage = request.getAssistantMessage();

        // 새로 생성 (없을 경우에만 실행됨)
        emotionConversationService.create(userEmail, date);
        EmotionConversation emotionConversation = emotionConversationService.findByUserEmailAndDate(userEmail, date);

        emotionConversationService.addUserConversation(emotionConversation, userMessage);
        emotionConversationService.addAssistantConversation(emotionConversation, assistantMessage);

        return ResponseEntity.ok()
                .body(emotionConversation);
    }


}
