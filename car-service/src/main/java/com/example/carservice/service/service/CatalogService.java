package com.example.carservice.service.service;

import com.example.carservice.service.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public CatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
}
