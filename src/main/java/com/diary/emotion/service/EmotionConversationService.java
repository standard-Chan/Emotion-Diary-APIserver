package com.diary.emotion.service;

import com.diary.emotion.domain.EmotionConversation;
import com.diary.emotion.repository.EmotionConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmotionConversationService {

    private final EmotionConversationRepository emotionConversationRepository;

    public EmotionConversation findByUserEmailAndDate(String userEmail, String date) {
        return emotionConversationRepository.findByUserEmailAndDate(userEmail, date)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] : User email and Date are not mapping Conversation."));
    }

    // 데이터가 존재하지 않을 경우에만 생성
    public void create(String userEmail, String date) {
        Optional<EmotionConversation> emotionConversation = emotionConversationRepository.findByUserEmailAndDate(userEmail, date);
        if (emotionConversation.isEmpty()) {
            EmotionConversation newEmotionConversation = new EmotionConversation(userEmail, date);
            emotionConversationRepository.save(newEmotionConversation);
        }
    }

    public void addUserConversation(EmotionConversation emotionConversation, String userMessage) {
        EmotionConversation updatedConversation = emotionConversation.addUserConversation(userMessage);
        emotionConversationRepository.save(updatedConversation);
    }

    public void addAssistantConversation(EmotionConversation emotionConversation, String assistantMessage) {
        EmotionConversation updatedConversation = emotionConversation.addAssistantConversation(assistantMessage);
        emotionConversationRepository.save(updatedConversation);
    }

    public EmotionConversation getEmotionConversation(String userEmail, String date) {
        Optional<EmotionConversation> conversation = emotionConversationRepository.findByUserEmailAndDate(userEmail, date);
        return conversation.orElseThrow(() -> new IllegalArgumentException("[ERROR] : User email and Date are not mapping Conversation."));
    }


}
