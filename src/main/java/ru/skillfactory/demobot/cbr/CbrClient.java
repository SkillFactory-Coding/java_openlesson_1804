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


    public String getRate(String input) {

        String response = restTemplate.getForEntity(crbUrl, String.class).getBody();
        try {
            input = input.toUpperCase();
            Map<String, Map> valutes = (Map<String, Map>) objectMapper.readValue(response, Map.class).get("Valute");
            String name = valutes.get(input).get("Name").toString();
            String rate = valutes.get(input).get("Value").toString();
            log.info("Get rate for {} = {}", name, rate);
            return rate;
        } catch (JsonProcessingException e) {
            log.error("Error in get valute rate!", e);
            throw new NotFoundException("Error in get valute rate");
        }
    }



}
