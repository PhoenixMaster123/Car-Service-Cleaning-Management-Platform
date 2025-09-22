package com.example.carservice.feedback.repository;

import com.example.carservice.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
