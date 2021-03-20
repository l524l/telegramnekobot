package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@TheCommand(name = "addadmin", description = "Добавляет админа", required_role = UserRoles.OWNER)
public class AddAdmin extends Command {
    @Autowired
    private BotSettings settings;

    protected AddAdmin() throws BotException {
        super(AddAdmin.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        SendMessage sendMessage;
        String chat_id = getChatID(update);
        String[] params = getText(update).split(" ");
        if (params.length >= 2 && params[1].trim().length() > 3){
            if (settings.addAdmin(params[1])){
                settings.saveSettings();
                sendMessage = SendMessage.builder()
                        .chatId(chat_id)
                        .text("Администратор добавлен")
                        .build();
            } else {
                sendMessage = SendMessage.builder()
                        .chatId(chat_id)
                        .text("Этот администратор уже был добавлен добавлен")
                        .build();
            }

        }else {
            sendMessage = SendMessage.builder()
                    .chatId(chat_id)
                    .text("Убедитесь в правильности параметра. Длина id должна быть больше 3 символов")
                    .build();
        }
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}