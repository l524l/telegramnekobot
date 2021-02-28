package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class BotSettingsConfig {
    private final Logger logger = LoggerFactory.getLogger(BotSettingsConfig.class);
    @Value("${telegram.webhook.bot.owner}")
    private String owner;
    @Value("${telegram.webhook.bot.botToken}")
    private String token;
    @Value("${telegram.webhook.bot.botPath}")
    private String botPath;
    @Value("${telegram.webhook.bot.botUsername}")
    private String botUsername;

    @Bean
    public BotSettings getBotSettings(){
        BotSettings settings;
        File file = new File("./settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!file.exists()){
                file.createNewFile();

                settings = BotSettings.builder()
                        .botToken(token)
                        .botUsername(botUsername)
                        .botPath(botPath)
                        .ownerID(owner)
                        .adminList(new ArrayList<>())
                        .workMode(WorkMode.SFW)
                        .build();

                objectMapper.writeValue(file,settings);

                logger.info("No settings file, creating default file");

                return settings;
            }else {
                settings = objectMapper.readValue(file, BotSettings.class);
                logger.info("Loaded settings file");
                return settings;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
