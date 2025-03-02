package com.diary.emotion.repository;

import com.diary.emotion.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    public Optional<Emotion> findByUserEmailAndDate(String userEmail, String Date);
}
