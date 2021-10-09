package com.github.l524l.telegramnekobot.nekosapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.nekosapi.impl.NekosFunApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Configuration
public class NekoApiConfig {

    @Bean
    public NekosFunApi createNekosFunApi() throws BotException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource urlResource = new ClassPathResource("json/api/nekocategories.json");
            NekosFunApi nekosFunApi = objectMapper.readValue(urlResource.getInputStream(), new TypeReference<NekosFunApi>() {});
            return nekosFunApi;
        } catch (IOException e) {
            throw new BotException("Fail loading categories file", e);
        }
    }
}

