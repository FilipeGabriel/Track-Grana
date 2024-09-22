package io.filipegabriel.track_grana_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Configura CORS para todos os endpoints
                        .allowedOrigins("http://localhost:4200")  // Endereço do seu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
