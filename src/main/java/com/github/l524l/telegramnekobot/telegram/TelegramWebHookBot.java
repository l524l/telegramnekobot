package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.settings.BotSettings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramWebHookBot extends TelegramWebhookBot {
    private final BotSettings settings;
    private final CommandHandler commandHandler;

    public TelegramWebHookBot(BotSettings settings, CommandHandler commandHandler) {
        this.settings = settings;
        this.commandHandler = commandHandler;
        try {
            if (!getWebhookInfo().getUrl().equals(settings.getBotPath())){
                SetWebhook setWebhook = new SetWebhook(settings.getBotPath());
                setWebhook(setWebhook);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return settings.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return settings.getBotToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        try {
            if (update.hasMessage() || update.hasCallbackQuery()) commandHandler.execute(update);
        }catch (Throwable e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getBotPath() {
        return settings.getBotPath();
    }
}