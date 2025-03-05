package com.diary.emotion.controller;

import com.diary.emotion.domain.Emotion;
import com.diary.emotion.dto.UserAndDateRequest;
import com.diary.emotion.dto.emotion.AddEmotionRequest;
import com.diary.emotion.dto.emotion.EmotionResponse;
import com.diary.emotion.repository.EmotionRepository;
import com.diary.emotion.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/emotion")
public class EmotionApiController {

    private final EmotionService emotionService;

    @PostMapping("/result")
    public ResponseEntity<EmotionResponse> createEmotions (@RequestBody AddEmotionRequest request) {
        emotionService.create(request.getUserEmail(), request.getDate(), request.getAdvice(), request.getEmotions(), request.getReasons(), request.getScores());

        EmotionResponse emotionResponse = EmotionResponse.builder()
                .userEmail(request.getUserEmail())
                .date(request.getDate())
                .advice(request.getAdvice())
                .emotions(request.getEmotions())
                .reasons(request.getReasons())
                .scores(request.getScores())
                .build();

        return ResponseEntity.status(201)
                .body(emotionResponse);
    }

    @GetMapping("/result/day")
    public ResponseEntity<EmotionResponse> getDayEmotions (@RequestParam String email, @RequestParam String date) {
        Emotion emotion = emotionService.findByUserEmailAndDate(email, date);

        EmotionResponse emotionResponse = EmotionResponse.builder()
                .userEmail(emotion.getUserEmail())
                .date(emotion.getDate())
                .advice(emotion.getAdvice())
                .emotions(emotion.getEmotions())
                .reasons(emotion.getReasons())
                .scores(emotion.getScores())
                .build();

        return ResponseEntity.ok()
                .body(emotionResponse);
    }

    @GetMapping("/result/month")
    public ResponseEntity<List<EmotionResponse>> getMonthEmotion (@RequestParam String email, @RequestParam String date) {
        String yearMonth = date.substring(0, 6); // 202503 추출 <- 202050302
        String userEmail = email;

        ArrayList<Emotion> emotionList = emotionService.findByDateStartingWithAndUserEmail(yearMonth, userEmail);
        List<EmotionResponse> emotions = emotionList.stream()
                .map(emotion -> new EmotionResponse(
                        emotion.getUserEmail(),
                        emotion.getDate(),
                        emotion.getAdvice(),
                        emotion.getEmotions(),
                        emotion.getReasons(),
                        emotion.getScores()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(emotions);
    }

}
