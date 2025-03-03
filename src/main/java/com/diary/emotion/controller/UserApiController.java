package com.diary.emotion.controller;

import com.diary.emotion.config.JwtTokenProvider;
import com.diary.emotion.domain.User;
import com.diary.emotion.dto.User.JwtResponse;
import com.diary.emotion.dto.User.LoginUserRequest;
import com.diary.emotion.dto.User.SignupUserRequest;
import com.diary.emotion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody SignupUserRequest request){
        String email = request.getEmail();
        String password = request.getPassword();

        // 이미 이메일이 존재할 경우
        if (userService.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 가입된 이메일입니다.");
        }

        // 회원가입
        userService.signup(email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userService.findByEmail(email);
        String jwt = jwtTokenProvider.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
