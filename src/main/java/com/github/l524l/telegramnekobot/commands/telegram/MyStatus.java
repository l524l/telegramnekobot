package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@BotCommand("GET_USER_STATUS_COMMAND")
public class MyStatus extends Command {

    private final TelegramSender telegramSender;

    @Autowired
    public MyStatus(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }

    @Override
    public void execute() throws TelegramApiException {
        String roles = "";

        for (UserRole role:
             executor.getRoles()) {
            roles = roles + " " + role.toString();
        }

        SendMessage sendMessage = SendMessage
                .builder()
                .chatId(message.getChatId().toString())
                .text(String.format("Ваши роли: %s", roles))
                .build();

        telegramSender.execute(sendMessage);
    }
}
