package com.example.carservice.vehicle.model;

import com.example.carservice.booking.model.Booking;
import com.example.carservice.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, unique = true)
    private String vin;

    @Column(nullable = false)
    private String licensePlate;

    @ManyToOne
    private User owner;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehicle")
    private List<Booking> bookings = new ArrayList<>();
}
