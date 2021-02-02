package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@TheCommand(name = "default", description = "Выводится при отсутствии команды", required_role = UserRoles.USER)
public class Default extends Command {

    public Default() throws BotException {
        super(Default.class);
    }

    @Override
    public void execute(Update update) throws BotException {
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(update.getMessage().getChatId().toString())
                .text("Команда не распознана")
                .build();
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
