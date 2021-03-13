package com.github.l524l.telegramnekobot.settings;

import com.github.l524l.telegramnekobot.databases.entity.VariableSettingsOption;
import com.github.l524l.telegramnekobot.databases.repository.SettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Optional;

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

    @Autowired
    private SettingsRepository settingsRepository;

    @Bean
    public BotSettings getBotSettings(){
        BotSettings.ProgramSettingsBuilder builder = new BotSettings.ProgramSettingsBuilder();
        VariableSettingsOption settingsOption = new VariableSettingsOption();
        Optional<VariableSettingsOption> options = settingsRepository.findById("program_settings");

        builder.botToken(token)
                .botUsername(botUsername)
                .botPath(botPath)
                .ownerID(owner)
                .repository(settingsRepository);

        if (!options.isPresent()){
                    builder
                        .adminList(new ArrayList<>())
                        .workMode(WorkMode.SFW);

                settingsOption.setAdminList(new ArrayList<>());
                settingsOption.setWorkMode(WorkMode.SFW);

                settingsRepository.save(settingsOption);

                logger.info("No settings option in DB, creating default profile");

                return builder.build();
            }else {
                settingsOption = options.get();
                builder.workMode(settingsOption.getWorkMode());

                if (settingsOption.getAdminList() == null) builder.adminList(new ArrayList<>());
                    else builder.adminList(settingsOption.getAdminList());

                logger.info("Loaded settings from DB");
                return builder.build();
            }
    }
}
