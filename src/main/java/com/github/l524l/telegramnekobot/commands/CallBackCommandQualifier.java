package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.commands.telegram.TelegramCommandFactory;
import com.github.l524l.telegramnekobot.observers.NewCallbackObserver;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.user.BotUser;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallBackCommandQualifier implements NewCallbackObserver {

    private final CommandFactory commandFactory;
    private final TelegramSender telegramSender;
    private final Gson gson;

    public CallBackCommandQualifier(TelegramCommandFactory commandFactory, TelegramSender telegramSender, Gson gson) {
        this.commandFactory = commandFactory;
        this.telegramSender = telegramSender;
        this.gson = gson;
    }

    @Override
    public void onNewCallback(BotUser botUser, CallbackQuery callback) {
        CallbackData callbackData = gson.fromJson(callback.getData(), CallbackData.class);

        Command command = commandFactory.createCommand(callbackData.getType());
        command.setParameters(callbackData.getParameter());

        try {
            telegramSender.execute(AnswerCallbackQuery.builder().callbackQueryId(callback.getId()).build());

            command.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
