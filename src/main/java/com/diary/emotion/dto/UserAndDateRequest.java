package com.diary.emotion.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAndDateRequest {
    private String userEmail;
    private String date;
}
