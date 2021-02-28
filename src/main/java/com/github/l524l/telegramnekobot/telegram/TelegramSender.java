package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.settings.BotSettings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
public class TelegramSender extends DefaultAbsSender {
    private final BotSettings botConfig;

    public TelegramSender(BotSettings botConfig) {
        super(new DefaultBotOptions());
        this.botConfig = botConfig;
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}
