package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Deprecated
@TheCommand(name = "start", description = "Начало работы с ботом", required_role = UserRoles.USER)
public class Start extends Command {
    protected Start() throws BotException {
        super(Start.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(getChatID(update))
                .text("Привет! Развлечемся?!" +
                        "\nВот список основных команд:" +
                        "\n/photo {категория без скобок} - для отображения случайной картинки" +
                        "\n/categories - для отображения списка категорий" +
                        "\n/mystatus - ваш статус пользователя")
                .build();
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}