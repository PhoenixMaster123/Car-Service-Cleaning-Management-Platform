package com.example.carservice.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceType {

    // Car Wash & Detailing Services
    EXPRESS_WASH("Express Wash", ServiceCategory.WASH_AND_DETAIL),
    STANDARD_WASH("Standard Wash", ServiceCategory.WASH_AND_DETAIL),
    INTERIOR_CLEANING("Interior Cleaning", ServiceCategory.WASH_AND_DETAIL),
    FULL_SERVICE_WASH("Full-Service Wash", ServiceCategory.WASH_AND_DETAIL),
    PROFESSIONAL_DETAILING("Professional Detailing", ServiceCategory.WASH_AND_DETAIL),
    HEADLIGHT_RESTORATION("Headlight Restoration", ServiceCategory.WASH_AND_DETAIL),

    // Maintenance & Repair Services
    OIL_AND_FILTER_CHANGE("Oil and Filter Change", ServiceCategory.MAINTENANCE_AND_REPAIR),
    TIRE_ROTATION("Tire Rotation", ServiceCategory.MAINTENANCE_AND_REPAIR),
    TIRE_SWAP("Tire Change/Swap (e.g., Summer/Winter)", ServiceCategory.MAINTENANCE_AND_REPAIR),
    PUNCTURE_REPAIR("Tire Puncture Repair", ServiceCategory.MAINTENANCE_AND_REPAIR),
    BRAKE_PAD_REPLACEMENT("Brake Pad & Disc Replacement", ServiceCategory.MAINTENANCE_AND_REPAIR),
    BRAKE_FLUID_CHANGE("Brake Fluid Change", ServiceCategory.MAINTENANCE_AND_REPAIR),
    FLUID_TOP_UP("Fluid Checks & Top-Ups", ServiceCategory.MAINTENANCE_AND_REPAIR),
    BATTERY_REPLACEMENT("Battery Replacement", ServiceCategory.MAINTENANCE_AND_REPAIR),
    WIPER_BLADE_REPLACEMENT("Wiper Blade Replacement", ServiceCategory.MAINTENANCE_AND_REPAIR),

    // Diagnostics & Inspections
    VEHICLE_HEALTH_CHECK("Vehicle Health Check", ServiceCategory.DIAGNOSTICS_AND_INSPECTION),
    OBD_SCAN("On-Board Diagnostics (OBD) Scan", ServiceCategory.DIAGNOSTICS_AND_INSPECTION),
    SEASONAL_INSPECTION("Seasonal Inspection (Winter/Summer Prep)", ServiceCategory.DIAGNOSTICS_AND_INSPECTION),
    TUV_PRE_INSPECTION("TÜV Pre-Inspection", ServiceCategory.DIAGNOSTICS_AND_INSPECTION);

    private final String displayName;
    private final ServiceCategory category;
}

