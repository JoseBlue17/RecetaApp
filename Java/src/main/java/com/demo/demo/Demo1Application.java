package com.demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(scanBasePackages = {"com.demo","com.demo.controller", "com.demo.services", "com.demo.config"})
@EnableJpaRepositories(basePackages = "com.demo.repository")
@EntityScan(basePackages = "com.demo.entity")
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
    @Configuration
    public static class Myconfiguration{
        @Bean
        public WebMvcConfigurer corsConfigurer(){
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
                }
            };
        }
    }
}
