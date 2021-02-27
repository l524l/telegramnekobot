package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.nekosapi.NekoApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class ProgramSettingsFileConfig {
    private final Logger logger = LoggerFactory.getLogger(ProgramSettingsFileConfig.class);
    @Value("${telegram.webhook.bot.owner}")
    private String owner;

    @Bean
    public ProgramSettings getProgramSettings(){
        ProgramSettings settings;
        File file = new File("./settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (!file.exists()){
                file.createNewFile();
                settings = new ProgramSettings();
                settings.setOwner(owner);
                settings.setWorkMode(WorkMode.SFW);
                settings.setAdminList(new ArrayList<>());
                objectMapper.writeValue(file,settings);
                logger.info("No settings file, creating default file");
                return settings;
            }else {
                settings = objectMapper.readValue(file,ProgramSettings.class);
                logger.info("Loaded settings file");
                return settings;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
