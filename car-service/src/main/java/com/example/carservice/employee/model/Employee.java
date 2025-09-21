package com.example.carservice.employee.model;

import com.example.carservice.booking.model.Booking;
import com.example.carservice.invoice.model.Invoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    private String profilePicture;

    private LocalDateTime dateOfBirth;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmploymentStatus status;

    private LocalDateTime hireDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<Invoice> invoices;
}
