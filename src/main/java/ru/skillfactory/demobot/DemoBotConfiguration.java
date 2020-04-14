package ru.skillfactory.demobot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Configuration
public class DemoBotConfiguration {

    @Value("${proxy.host}")
    private String host;

    @Value("${proxy.port}")
    private int port;

    @Value("${proxy.user}")
    private String user;

    @Value("${proxy.pass}")
    private String pass;

    @Bean
    DefaultBotOptions botOptions() {
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        botOptions.setProxyHost(host);
        botOptions.setProxyPort(port);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return botOptions;
    }

    @Bean
    Authenticator auth() {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass.toCharArray());
            }
        };
        Authenticator.setDefault(auth);
        return auth;
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
