package com.diary.emotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Emotion Diary API Server Start!");
        SpringApplication.run(Main.class, args);
    }
}