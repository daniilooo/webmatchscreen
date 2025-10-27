package com.webmatchscreen.webmatchscreen;

import com.webmatchscreen.webmatchscreen.service.ApiServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WebmatchscreenApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebmatchscreenApplication.class, args);
    }
}
