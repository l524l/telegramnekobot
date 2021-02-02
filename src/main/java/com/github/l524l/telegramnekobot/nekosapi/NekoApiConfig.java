package com.github.l524l.telegramnekobot.nekosapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

@Configuration
public class NekoApiConfig {
    private final Logger logger = LoggerFactory.getLogger(NekoApiConfig.class);

    @Bean
    public NekosApi createNekoApi() throws NekosApiException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource urlResource = new ClassPathResource("./nekocategories.json");
            List<NekoCategory> list = objectMapper.readValue(urlResource.getInputStream(), new TypeReference<List<NekoCategory>>() {});
            logger.info("Loaded categories file");
            return new NekosApi(list);
        } catch (IOException e) {
            throw new NekosApiException("Fail loading categories file", 102 , e);
        }
    }
}

