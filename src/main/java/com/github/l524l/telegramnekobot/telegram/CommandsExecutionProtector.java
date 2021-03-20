package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

@Component
public class CommandsExecutionProtector {
    private final Logger logger = LoggerFactory.getLogger(CommandsExecutionProtector.class);
    @Autowired
    private BotSettings settings;
    @Autowired TelegramSender telegramSender;

    public void execute(Command command, Update update) throws BotException {
        BotUser executor = createExecutor(update);
        if (command.getRequired_role().isLessPriority(executor.getRole())) command.execute(update, executor);
        else {
            try {
                ClassPathResource resource = new ClassPathResource("/img/403.png");
                SendPhoto sendPhoto = SendPhoto.builder()
                        .chatId(Command.getChatID(update))
                        .photo(new InputFile(resource.getInputStream(), "403"))
                        .build();
                telegramSender.execute(sendPhoto);
            } catch (TelegramApiException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    private BotUser createExecutor(Update update) throws BotException {
        String username = Command.getFrom(update).getUserName();
        UserRoles role;
        List<String> adminList = settings.getAdminList();
        if (settings.getOwner().equals(username)) {
            role = UserRoles.OWNER;
        } else if (adminList.contains(username)){
            role = UserRoles.ADMIN;
        } else role = UserRoles.USER;
        return new BotUser(username, role);
    }
}
