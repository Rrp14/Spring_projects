package com.vector.crudapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allow frontend origin correctly (no specific paths)
        config.setAllowedOrigins(List.of("http://localhost:63342"));

        // ✅ Allow credentials (for cookies, auth headers)
        config.setAllowCredentials(true);

        // ✅ Allow all HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT","PATCH", "DELETE", "OPTIONS"));

        // ✅ Allow all headers (not just Authorization and Content-Type)
        config.setAllowedHeaders(List.of("*"));

        // ✅ Allow all exposed headers
        config.setExposedHeaders(List.of("*"));

        // ✅ Apply CORS settings to all endpoints
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
