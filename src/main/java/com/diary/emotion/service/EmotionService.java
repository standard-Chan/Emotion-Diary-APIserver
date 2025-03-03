package com.diary.emotion.service;

import com.diary.emotion.domain.Emotion;
import com.diary.emotion.domain.EmotionConversation;
import com.diary.emotion.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmotionService {
    private final EmotionRepository emotionRepository;

    public Emotion findByUserEmailAndDate(String userEmail, String date) {
        return emotionRepository.findByUserEmailAndDate(userEmail, date)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] : User email and Date are not mapping emotion."));
    }

    public ArrayList<Emotion> findByDateStartingWithAndUserEmail(String date, String userEmail) {
        Optional<ArrayList<Emotion>> optionalEmotions = emotionRepository.findByDateStartingWithAndUserEmail(date, userEmail);

        return optionalEmotions.orElseThrow(() -> new IllegalArgumentException("[ERROR] : 해당 유저의 해당 년/월 데이터가 존재하지 않습니다."));

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
