package com.diary.emotion.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private String email;
    private String password;
}
