package com.diary.emotion.dto.emotion;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AddEmotionRequest {
    private String userEmail;
    private String date;
    private ArrayList<String> emotions;
    private ArrayList<String> reasons;
    private ArrayList<Integer> scores;
}
