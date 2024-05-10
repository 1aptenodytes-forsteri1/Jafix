package com.example.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/message")
                .allowedOrigins("*") // Разрешенные источники (можете указать конкретные источники)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешенные HTTP методы
                .allowedHeaders("*"); // Разрешенные заголовки
    }
}