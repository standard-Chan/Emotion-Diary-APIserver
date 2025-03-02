package com.diary.emotion.repository;

import com.diary.emotion.domain.EmotionConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmotionConversationRepository extends JpaRepository<EmotionConversation, Long> {
    public Optional<EmotionConversation> findByUserEmailAndDate(String userEmail, String Date);
}