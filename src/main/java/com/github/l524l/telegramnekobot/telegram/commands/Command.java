package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public abstract class Command {
    @Autowired
    protected TelegramSender telegramSender;
    protected UserRoles required_role;
    protected String name;
    protected String description;

    protected Command(Class<? extends Command> command) throws BotException {
        if (command.isAnnotationPresent(TheCommand.class)){
            TheCommand theCommand = command.getAnnotation(TheCommand.class);
            required_role = theCommand.required_role();
            name = theCommand.name();
            description = theCommand.description();
        } else throw new BotException("Class " + command.getName() + " doesn't have annotation TheCommand", 1);
    }

    public abstract void execute(Update update, BotUser executor) throws BotException;

    public static String getChatID(Update update) throws BotException {
        if (update.hasMessage()) return update.getMessage().getChatId().toString();
        else if (update.hasCallbackQuery()) {
             return update.getCallbackQuery().getMessage().getChatId().toString();
        } else throw new BotException("Incorrect update!",3);
    }

    public static String getText(Update update) throws BotException {
        if (update.hasMessage()) return update.getMessage().getText();
        else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData();
        } else throw new BotException("Incorrect update!",3);
    }

    public static User getFrom(Update update) throws BotException {
        if (update.hasMessage()) return update.getMessage().getFrom();
        else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom();
        } else throw new BotException("Incorrect update!",3);
    }

    public UserRoles getRequired_role() {
        return required_role;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
