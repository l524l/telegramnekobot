package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import com.github.l524l.telegramnekobot.telegram.apimethod.SendPhotoByURL;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

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
        SendPhotoByURL sendPhoto = new SendPhotoByURL();
        sendPhoto.setChat_id(update.getMessage().getChatId().toString());
        sendPhoto.setPhoto("https://cdn.nekos.life/neko/neko_070.jpg");
        SendPhoto sendPhoto1 = new SendPhoto();
        sendPhoto1.setChatId(update.getMessage().getChatId().toString());
        sendPhoto1.setPhoto(new InputFile("https://cdn.nekos.life/neko/neko_070.jpg"));
        try {
            execute(sendPhoto1);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return sendPhoto;
    }

    @Override
    public String getBotPath() {
        return botConfig.getBotPath();
    }
}