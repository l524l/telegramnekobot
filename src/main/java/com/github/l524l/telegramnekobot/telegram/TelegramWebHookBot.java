package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import com.github.l524l.telegramnekobot.telegram.commands.Photo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramWebHookBot extends TelegramWebhookBot {
    private final TelegramBotConfig botConfig;
    private final ProgramSettings settings;
    private final NekosApi nekosApi;
    private final Photo photo;

    public TelegramWebHookBot(TelegramBotConfig botConfig, ProgramSettings settings, NekosApi nekosApi, Photo photo) {
        this.botConfig = botConfig;
        this.settings = settings;
        this.nekosApi = nekosApi;
        this.photo = photo;
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
        try {
            photo.execute(update);
        }catch (Throwable e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getBotPath() {
        return botConfig.getBotPath();
    }
}