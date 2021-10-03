package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.commands.telegram.TelegramCommandFactory;
import com.github.l524l.telegramnekobot.observers.NewMessageObserver;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandQualifier implements NewMessageObserver {

    private final TelegramCommandFactory commandFactory;
    private final Pattern pattern;

    @Autowired
    public CommandQualifier(TelegramCommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        pattern = Pattern.compile("(?<=^/)\\S+(?=\\s|$)");
    }

    @Override
    public void onNewMessage(BotUser user, Message message) {
        String messageText = message.getText();

        if (messageText.equals("")) return;

        CommandType type = parseCommandType(messageText);

        Command command = commandFactory.createCommand(type);
        command.setContext(user, message);

        try {
            command.execute();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public CommandType parseCommandType(String string){
        String command = "";
        try {
            Matcher matcher = pattern.matcher(string);

            matcher.find();
            command = matcher.group();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommandType.getByCommandName(command);
    }
}
