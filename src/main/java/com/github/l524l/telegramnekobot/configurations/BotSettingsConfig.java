package com.github.l524l.telegramnekobot.configurations;

import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

    @Bean
    public KeyboardTemplatesStore createKeyBordTemplates(ClassPathResource resource) throws IOException {
        KeyboardTemplatesStore keyBordTemplates = new KeyboardTemplatesStore();

        for (File file:
                resource.getFile().listFiles()) {

            StringBuilder builder = new StringBuilder();

            Files.readAllLines(file.toPath()).forEach(builder::append);

            KeyboardTemplate template = new KeyboardTemplate(builder.toString());
            keyBordTemplates.addTemplate(file.getName(), template);
        }
        return keyBordTemplates;
    }

    @Bean
    public ClassPathResource templates() throws IOException {
        return new ClassPathResource("\\json\\templates");
    }
}
