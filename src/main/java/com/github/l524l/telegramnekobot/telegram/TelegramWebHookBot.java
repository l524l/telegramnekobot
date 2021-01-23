package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramWebHookBot extends TelegramWebhookBot {
    private final TelegramBotConfig botConfig;
    private final ProgramSettings settings;

    public TelegramWebHookBot(TelegramBotConfig botConfig, ProgramSettings settings) {
        this.botConfig = botConfig;
        this.settings = settings;
        try {
            if (!getWebhookInfo().getUrl().equals(botConfig.getBotPath())){
                SetWebhook setWebhook = new SetWebhook(botConfig.getBotPath());
                setWebhook(setWebhook);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        SendPhoto sendPhoto1 = new SendPhoto();
        sendPhoto1.setChatId(update.getMessage().getChatId().toString());
        sendPhoto1.setPhoto(new InputFile("https://cdn.nekos.life/neko/neko_070.jpg"));
        try {
            execute(sendPhoto1);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return botConfig.getBotPath();
    }
}