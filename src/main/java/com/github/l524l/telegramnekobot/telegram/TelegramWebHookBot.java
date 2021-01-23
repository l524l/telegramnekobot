package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramWebHookBot extends TelegramWebhookBot {
    private final TelegramBotConfig botConfig;
    private final ProgramSettings settings;
    private final NekosApi nekosApi;

    public TelegramWebHookBot(TelegramBotConfig botConfig, ProgramSettings settings, NekosApi nekosApi) {
        this.botConfig = botConfig;
        this.settings = settings;
        this.nekosApi = nekosApi;
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
            SendPhoto sendPhoto1 = new SendPhoto();
            sendPhoto1.setChatId(update.getMessage().getChatId().toString());
            String[] s = update.getMessage().getText().split(" ");

            sendPhoto1.setPhoto(new InputFile(nekosApi.execute(s[1])));
            try {
                execute(sendPhoto1);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
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