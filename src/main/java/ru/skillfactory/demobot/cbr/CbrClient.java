package ru.skillfactory.demobot.cbr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.NotFoundException;
import java.util.Map;

/**
 * Клиент для сервиса CBR
 * @link https://www.cbr-xml-daily.ru/daily_json.js
 */
@Component
public class CbrClient {
    private final Logger log = LoggerFactory.getLogger(CbrClient.class);

    @Value("${cbr.url}")
    private String crbUrl;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public CbrClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    /**
     * Получим стоимость за единицу валюты в рублях по актуальному курсу
     * @param input код валюты (usd, eur...)
     * @return Стоимость в рублях строкой
     */
    public String getRate(String input) {
        try {
            // Осуществим REST вызов
            String response = restTemplate.getForEntity(crbUrl, String.class).getBody();

            // Так как в сервисе всегда коды USD
            input = input.toUpperCase();
            // Преобразуем строку в Map
            Map<String, Map> valutes = (Map<String, Map>) objectMapper.readValue(response, Map.class).get("Valute");
            String name = valutes.get(input).get("Name").toString();
            String rate = valutes.get(input).get("Value").toString();
            log.info("Get rate for {} = {}", name, rate);
            return rate;
        } catch (Exception e) {
            // Обработаем ошибку
            log.error("Error in get valute rate!", e);
            throw new NotFoundException("Error in get valute rate");
        }
    }



}
