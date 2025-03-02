package com.diary.emotion.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@Entity
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String date;

    private ArrayList<String> emotions;

    private ArrayList<String> reasons;

    private ArrayList<Integer> scores;

    @Builder
    public Emotion(String userEmail, String date, ArrayList<String> emotions,
                   ArrayList<String> reasons, ArrayList<Integer> scores) {
        this.userEmail = userEmail;
        this.date = date;
        this.emotions = emotions;
        this.reasons = reasons;
        this.scores = scores;
    }

}
