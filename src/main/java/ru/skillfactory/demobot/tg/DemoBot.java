package ru.skillfactory.demobot.tg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.skillfactory.demobot.cbr.CbrClient;

import java.time.LocalDate;

@Component
public class DemoBot extends TelegramLongPollingBot {
    private final Logger log = LoggerFactory.getLogger(DemoBot.class);

    @Value("${tg.user}")
    private String user;

    @Value("${tg.token}")
    private String token;

    private final CbrClient cbrClient;

    @Autowired
    public DemoBot(DefaultBotOptions botOptions, CbrClient cbrClient) {
        super(botOptions);
        this.cbrClient = cbrClient;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Get {}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.startsWith("/")) {
                processCommand(messageText, chatId);
            } else if (messageText.toLowerCase().contains("java")) {
                sendMessage("Хотите стать java-программистом? Приходите skillfactory.ru/java!", chatId);
            } else {
                // just echo
                sendMessage(messageText + "?)", chatId);
            }
        }
    }

    private void processCommand(String command, long chatId) {
        Command cmd = Command.fromValue(command);

        switch (cmd) {
            case HELP:
                sendMessage("Это справка, тут можно написать," +
                        " что умеет бот и как этим пользоваться\n" +
                        "Это демо бот для урока Skillfactory", chatId);
                break;
            case RATE:
                // work with rate command
                if (command.split(" ").length != 2) {
                    sendMessage("Передайте код валюты", chatId);
                    return;
                }
                String valute = command.split(" ")[1];

                String rate = cbrClient.getRate(valute);

                StringBuilder sb = new StringBuilder()
                        .append("Курс ")
                        .append(valute)
                        .append(" на ")
                        .append(LocalDate.now())
                        .append(" = ")
                        .append(rate);

                sendMessage(sb.toString(), chatId );
                break;
            default:
                sendMessage("Простите, меня еще не научили работать с этой командой (", chatId);
                break;
        }

    }

    private void sendMessage(String text, long chatId) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error send request: {}", e.getMessage(), e);
            //e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return user;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
