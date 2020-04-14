package ru.skillfactory.demobot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootTest
class DemoBotApplicationTests {

    static {
        ApiContextInitializer.init();
    }

    @Test
    void contextLoads() {
    }

}
