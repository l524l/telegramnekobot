package com.github.l524l.telegramnekobot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author pithera
 * @version 1.0
 * Simple Webhook example
 */
@Component
public class WebHookExampleHandlers extends TelegramWebhookBot {
    private final TelegramBotConfig botConfig;

    public WebHookExampleHandlers(TelegramBotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("asd");
        return sendMessage;
    }

    @Override
    public String getBotPath() {
        return botConfig.getBotPath();
    }
}