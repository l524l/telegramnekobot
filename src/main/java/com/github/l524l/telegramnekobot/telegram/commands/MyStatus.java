package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Deprecated
@Component
@TheCommand(name = "mystatus", description = "Выводит статус пользователя", required_role = UserRoles.USER)
public class MyStatus extends Command{
    protected MyStatus() throws BotException {
        super(MyStatus.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        SendMessage sendMessage = SendMessage
                .builder()
                    .chatId(getChatID(update))
                    .text(String.format("Ваш статус: %s", executor.getRole()))
                    .build();
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
