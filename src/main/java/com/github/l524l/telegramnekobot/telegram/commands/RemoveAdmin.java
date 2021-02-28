package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.CommandExecutor;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@TheCommand(name = "removeadmin", description = "Добавляет админа", required_role = UserRoles.OWNER)
public class RemoveAdmin extends Command {
    @Autowired
    private BotSettings settings;

    protected RemoveAdmin() throws BotException {
        super(RemoveAdmin.class);
    }

    @Override
    public void execute(Update update, CommandExecutor executor) {
        SendMessage sendMessage;
        String chat_id = update.getMessage().getChatId().toString();
        String[] params = update.getMessage().getText().split(" ");
        if (params.length >= 2 && params[1].trim().length() > 3){
            if (settings.removeAdmin(params[1])){
                settings.saveSettings();
                sendMessage = SendMessage.builder()
                        .chatId(chat_id)
                        .text("Администратор удалён")
                        .build();
            } else{
                sendMessage = SendMessage.builder()
                        .chatId(chat_id)
                        .text("Этот администратор отсутствует в списке")
                        .build();
            }

        } else {
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