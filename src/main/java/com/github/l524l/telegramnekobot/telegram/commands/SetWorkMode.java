package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.settings.WorkMode;
import com.github.l524l.telegramnekobot.telegram.CommandExecutor;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@TheCommand(name = "setworkmode", description = "Устанавливает workmode", required_role = UserRoles.OWNER)
public class SetWorkMode extends Command {

    @Autowired
    private BotSettings botSettings;

    protected SetWorkMode() throws BotException {
        super(SetWorkMode.class);
    }

    @Override
    public void execute(Update update, CommandExecutor executor) {
        String chat_id = update.getMessage().getChatId().toString();
        String[] params = update.getMessage().getText().split(" ");
        SendMessage sendMessage;
        if (params.length >= 2 && params[1].matches("(NSFW)|(SFW)")) {
            botSettings.setWorkMode(WorkMode.valueOf(params[1]));
            botSettings.saveSettings();
            sendMessage = SendMessage.builder()
                    .text(String.format("WorkMode изменён на: %s", botSettings.getWorkMode()))
                    .chatId(chat_id)
                    .build();
            try {
                telegramSender.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else{
            sendMessage = SendMessage.builder()
                    .text("Вы не указали параметр(SFW/NSFW)")
                    .chatId(chat_id)
                    .build();
        }
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}