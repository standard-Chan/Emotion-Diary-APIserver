package com.diary.emotion.repository;

import com.diary.emotion.domain.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    public Optional<Emotion> findByUserEmailAndDate(String userEmail, String Date);

    // 1개월간 데이터를 찾기 위한 메서드
    public Optional<ArrayList<Emotion>> findByDateStartingWithAndUserEmail(String date, String userEmail);
}
