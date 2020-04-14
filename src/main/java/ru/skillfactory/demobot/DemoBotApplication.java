package ru.skillfactory.demobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class DemoBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBotApplication.class, args);
    }
}
