package com.diary.emotion.service;

import com.diary.emotion.domain.Emotion;
import com.diary.emotion.domain.EmotionConversation;
import com.diary.emotion.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public Emotion findByUserEmailAndDate(String userEmail, String date) {
        return emotionRepository.findByUserEmailAndDate(userEmail, date)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] : User email and Date are not mapping emotion."));
    }

    public Emotion create(String userEmail, String date, ArrayList<String> emotions,
                          ArrayList<String> reasons, ArrayList<Integer> scores) {
        Emotion emotion = Emotion.builder()
                .userEmail(userEmail)
                .date(date)
                .emotions(emotions)
                .reasons(reasons)
                .scores(scores)
                .build();

        emotionRepository.save(emotion);
        return emotion;
    }
}
