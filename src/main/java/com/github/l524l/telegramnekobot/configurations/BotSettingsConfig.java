package com.github.l524l.telegramnekobot.configurations;

import com.github.l524l.telegramnekobot.settings.BotSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotSettingsConfig {
    private final Logger logger = LoggerFactory.getLogger(BotSettingsConfig.class);

    @Value("${telegram.webhook.bot.botToken}")
    private String token;
    @Value("${telegram.webhook.bot.botPath}")
    private String botPath;
    @Value("${telegram.webhook.bot.botUsername}")
    private String botUsername;

    @Bean
    public BotSettings getBotSettings(){
        return new BotSettings(token, botUsername, botPath);
    }
}
