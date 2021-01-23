package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ProgramSettingsFileConfig {

    @Value("${telegram.webhook.bot.owner}")
    private String owner;

    @Bean
    public ProgramSettings getProgramSettings(){
        ProgramSettings settings;
        //File file = new File("./settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        settings = new ProgramSettings();
        settings.setOwner(owner);
        settings.setWorkMode(WorkMode.LIGHT);
        settings.setAdminList(new ArrayList<>());
        return settings;
    }
}
