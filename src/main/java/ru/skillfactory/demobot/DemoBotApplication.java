package ru.skillfactory.demobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class DemoBotApplication {

    /**
     * Наш основной метод для запуска приложения
     * Тут будет вся магия
     */
    public static void main(String[] args) {
        // Проинициализируем ApiContext, без этого боты не будут работать
        ApiContextInitializer.init();
        SpringApplication.run(DemoBotApplication.class, args);
    }
}
