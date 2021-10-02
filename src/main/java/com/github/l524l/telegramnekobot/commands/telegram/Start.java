package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@BotCommand("START_COMMAND")
public class Start extends Command {

    private final TelegramSender telegramSender;

    @Autowired
    public Start(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }

    @Override
    public void execute() throws TelegramApiException {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(message.getChatId().toString())
                .text("Привет! Развлечемся?!" +
                        "\nВот список основных команд:" +
                        "\n/photo {категория без скобок} - для отображения случайной картинки" +
                        "\n/categories - для отображения списка категорий" +
                        "\n/mystatus - ваш статус пользователя")
                .build();

        telegramSender.execute(sendMessage);
    }
}
