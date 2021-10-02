package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@BotCommand("DEFAULT_COMMAND")
public class Default extends Command {

    private final TelegramSender telegramSender;

    @Autowired
    public Default(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }

    @Override
    public void execute() throws TelegramApiException {
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(message.getChatId().toString())
                .text("Команда не распознана")
                .build();

        telegramSender.execute(sendMessage);
    }
}
