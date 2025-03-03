package com.diary.emotion.controller;

import com.diary.emotion.domain.Emotion;
import com.diary.emotion.dto.UserAndDateRequest;
import com.diary.emotion.dto.emotion.AddEmotionRequest;
import com.diary.emotion.dto.emotion.EmotionResponse;
import com.diary.emotion.repository.EmotionRepository;
import com.diary.emotion.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmotionApiController {

    private final EmotionService emotionService;

    @PostMapping("/emotions")
    public ResponseEntity<EmotionResponse> createEmotions (@RequestBody AddEmotionRequest request) {
        emotionService.create(request.getUserEmail(), request.getDate(), request.getEmotions(), request.getReasons(), request.getScores());

        EmotionResponse emotionResponse = EmotionResponse.builder()
                .userEmail(request.getUserEmail())
                .date(request.getDate())
                .emotions(request.getEmotions())
                .reasons(request.getReasons())
                .scores(request.getScores())
                .build();

        return ResponseEntity.status(201)
                .body(emotionResponse);
    }

    @PostMapping("/emotions/day")
    public ResponseEntity<EmotionResponse> getDayEmotions (@RequestBody UserAndDateRequest request) {
        Emotion emotion = emotionService.findByUserEmailAndDate(request.getUserEmail(), request.getDate());

        EmotionResponse emotionResponse = EmotionResponse.builder()
                .userEmail(emotion.getUserEmail())
                .date(emotion.getDate())
                .emotions(emotion.getEmotions())
                .reasons(emotion.getReasons())
                .scores(emotion.getScores())
                .build();

        return ResponseEntity.ok()
                .body(emotionResponse);
    }

    @PostMapping("/emotions/month")
    public ResponseEntity<List<EmotionResponse>> getMonthEmotion (@RequestBody UserAndDateRequest request) {
        String yearMonth = request.getDate().substring(0, 6); // 202503 추출 <- 202050302
        String userEmail = request.getUserEmail();

        ArrayList<Emotion> emotionList = emotionService.findByDateStartingWithAndUserEmail(yearMonth, userEmail);
        List<EmotionResponse> emotions = emotionList.stream()
                .map(emotion -> new EmotionResponse(
                        emotion.getUserEmail(),
                        emotion.getDate(),
                        emotion.getEmotions(),
                        emotion.getReasons(),
                        emotion.getScores()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(emotions);
    }

}
