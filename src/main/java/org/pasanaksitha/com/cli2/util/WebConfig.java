package org.pasanaksitha.com.cli2.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS) policies.
 * This allows the backend to accept HTTP requests from the React frontend running on localhost.
 */
@Configuration
public class WebConfig {

    /**
     * Bean definition for configuring CORS mappings.
     * This method enables CORS for all endpoints and allows specific origins, methods, and headers.
     *
     * @return A WebMvcConfigurer instance with the defined CORS settings.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             * Configures CORS mappings to allow requests from the React frontend
             * running on `http://localhost:5173`.
             *
             * @param registry The CORS registry to configure mappings.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}