package com.diary.emotion.controller;

import com.diary.emotion.domain.EmotionConversation;
import com.diary.emotion.dto.UserAndDateRequest;
import com.diary.emotion.dto.emotionConversation.AddEmotionConversationRequest;
import com.diary.emotion.dto.emotionConversation.EmotionConversationResponse;
import com.diary.emotion.service.EmotionConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmotionConversationApiController {

    private final EmotionConversationService emotionConversationService;

    // emotion 대화 전송
    @PostMapping("/emotion/conversation")
    public ResponseEntity<EmotionConversationResponse> updateConversation(@RequestBody AddEmotionConversationRequest request) {
        String userEmail = request.getUserEmail();
        String date = request.getDate();
        String userMessage = request.getUserMessage();
        String assistantMessage = request.getAssistantMessage();

        // 새로 생성 (없을 경우에만 실행됨)
        emotionConversationService.create(userEmail, date);
        EmotionConversation emotionConversation = emotionConversationService.findByUserEmailAndDate(userEmail, date);

        emotionConversation = emotionConversationService.addUserConversation(emotionConversation, userMessage);
        emotionConversation = emotionConversationService.addAssistantConversation(emotionConversation, assistantMessage);

        EmotionConversationResponse response = new EmotionConversationResponse(emotionConversation.getUserConversation(), emotionConversation.getAssistantConversation());
        return ResponseEntity.status(201)
                .body(response);
    }

    // 대화 내역 요청
    @PostMapping("/emotion/conversation/list")
    public ResponseEntity<EmotionConversationResponse> getConversation(@RequestBody UserAndDateRequest request) {
        String userEmail = request.getUserEmail();
        String date = request.getDate();
        EmotionConversation emotionConversation = emotionConversationService.findByUserEmailAndDate(userEmail, date);

        EmotionConversationResponse response = new EmotionConversationResponse(emotionConversation.getUserConversation(), emotionConversation.getAssistantConversation());

        return ResponseEntity.ok()
                .body(response);
    }

}
