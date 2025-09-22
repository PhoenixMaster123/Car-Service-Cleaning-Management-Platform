package com.example.carservice.service.model;

import com.example.carservice.booking.model.Booking;
import com.example.carservice.feedback.model.Feedback;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceCategory category;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private Integer estimatedDurationInMinutes;

    @ManyToMany(mappedBy = "services")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "service")
    private List<Feedback> feedbacks = new ArrayList<>();
}
