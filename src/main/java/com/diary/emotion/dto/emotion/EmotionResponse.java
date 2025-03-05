package com.diary.emotion.dto.emotion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EmotionResponse {
    private String userEmail;
    private String date;
    private String advice;
    private ArrayList<String> emotions;
    private ArrayList<String> reasons;
    private ArrayList<Integer> scores;

    @Builder
    public EmotionResponse(String userEmail, String date, String advice, ArrayList<String> emotions,
                   ArrayList<String> reasons, ArrayList<Integer> scores) {
        this.userEmail = userEmail;
        this.date = date;
        this.advice = advice;
        this.emotions = emotions;
        this.reasons = reasons;
        this.scores = scores;
    }
}
