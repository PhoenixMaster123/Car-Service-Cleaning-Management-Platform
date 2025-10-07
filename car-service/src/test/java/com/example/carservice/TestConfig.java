package com.example.carservice;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

import java.util.HashMap;

@TestConfiguration
public class TestConfig {

    @Bean
    public SessionRepository<MapSession> sessionRepository() {
        return new MapSessionRepository(new HashMap<>());
    }
}
