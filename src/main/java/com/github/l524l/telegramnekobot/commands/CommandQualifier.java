package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.commands.telegram.TelegramCommandFactory;
import com.github.l524l.telegramnekobot.exceptions.CommandNotFindException;
import com.github.l524l.telegramnekobot.observers.NewMessageObserver;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandQualifier implements NewMessageObserver {

    private final TelegramCommandFactory commandFactory;

    @Autowired
    public CommandQualifier(@Qualifier("TGFactory") TelegramCommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public void onNewMessage(BotUser user, Message message) {
        String messageText = message.getText();

        if (messageText.equals("")) return;

        try {
            Command command = commandFactory.createCommand(message.getText());

            if (message.hasReplyMarkup()) {
                command.setParameters(message.getReplyMarkup().getKeyboard().get(0).get(0).getText());
            }

            command.execute();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }
    }
}
