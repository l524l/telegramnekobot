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
@TheCommand(name = "adminslist", description = "Выводит список админов", required_role = UserRoles.OWNER)
public class AdminsList extends Command {
    protected AdminsList() throws BotException {
        super(AdminsList.class);
    }
    @Autowired
    private BotSettings botSettings;

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        StringBuilder adminlist = new StringBuilder();
        adminlist.append("Список администраторов:\n");
        botSettings.getAdminList().forEach((x) ->
            adminlist.append(String.format("\\- %s\n",x))
        );
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(getChatID(update))
                .text(adminlist.toString())
                .parseMode("MarkdownV2")
                .build();
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}