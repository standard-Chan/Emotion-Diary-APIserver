package com.diary.emotion.dto.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupUserRequest {
    private String email;
    private String password;
}
