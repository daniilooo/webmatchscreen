package com.webmatchscreen.webmatchscreen.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvLoader {

    @Bean
    public Dotenv dotenv(){
        return Dotenv.configure()
                .ignoreIfMissing()
                .load();
    }
}
