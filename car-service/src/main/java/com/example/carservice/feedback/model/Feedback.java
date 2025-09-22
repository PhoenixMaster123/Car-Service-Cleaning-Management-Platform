package com.example.carservice.feedback.model;

import com.example.carservice.service.model.Service;
import com.example.carservice.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String comment;

    @Column(nullable = false)
    private int rating;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    private User owner;

    @ManyToOne
    private Service service;
}
