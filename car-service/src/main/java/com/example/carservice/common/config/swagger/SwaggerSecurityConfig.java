package com.example.carservice.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@Profile("!prod")
public class SwaggerSecurityConfig {

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity aHttp) throws Exception {
        aHttp.csrf(AbstractHttpConfigurer::disable)
          .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers("/swagger-ui.html").authenticated()
            .requestMatchers("/swagger-ui/**").authenticated()
            .requestMatchers("/swagger-resources").authenticated()
            .requestMatchers("/v3/api-docs").authenticated()
            .anyRequest().permitAll())
          .httpBasic((httpBasicCustomizer) -> httpBasicCustomizer.realmName("car-service"))
          .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         return aHttp.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder aPasswordEncoder) {
        InMemoryUserDetailsManager tInMemoryManager = new InMemoryUserDetailsManager();
        tInMemoryManager.createUser(User.withUsername("user")
          .password(aPasswordEncoder.encode("password"))
          .roles("USER")
          .build());

        return tInMemoryManager;
    }
}
