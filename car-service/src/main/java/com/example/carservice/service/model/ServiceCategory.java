package com.example.carservice.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceCategory {
    WASH_AND_DETAIL("Car Wash & Detailing"),
    MAINTENANCE_AND_REPAIR("Maintenance & Repair"),
    DIAGNOSTICS_AND_INSPECTION("Diagnostics & Inspection");

    private final String displayName;
}
