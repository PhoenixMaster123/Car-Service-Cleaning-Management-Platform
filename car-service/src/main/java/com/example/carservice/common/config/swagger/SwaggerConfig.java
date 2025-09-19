package com.example.carservice.common.config.swagger;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Customizer to sort the controller tags alphabetically in the Swagger UI.
     * @return OpenApiCustomizer
     */
    @Bean
    public OpenApiCustomizer sortTagsAlphabetically() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.setTags(
                        openApi.getTags()
                                .stream()
                                .sorted(Comparator.comparing(tag -> stripAccents(tag.getName())))
                                .collect(Collectors.toList())
                );
            }
        };
    }

    /**
     * Helper method to remove accents from a string for proper sorting.
     */
    private String stripAccents(String str) {
        if (str == null) {
            return null;
        }
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}