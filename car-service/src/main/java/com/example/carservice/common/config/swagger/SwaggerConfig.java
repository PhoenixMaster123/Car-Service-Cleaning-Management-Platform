package com.example.carservice.common.config.swagger;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.stream.Collectors;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Profile("swagger")
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

    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info().title("Car Service")
                        .version("1")
                        .description("""
                                Welcome to the Car Service REST-API documentation v1.\
                                

                                Most endpoints can be executed by using the Try out function of swagger. You can find this function on each \
                                endpoint specification. The request can be configured by changing the available parameter values. \
                                Please be extremely carefully with this function on production environments.
                                
                                Currently, there is no authentification for the endpoints necessary.""")
                        .contact(new Contact()
                                .name("Car Wash Service Haris")
                                .url("https://carwash-haris.example.com")
                                .email("carwash-haris.example.com")));
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