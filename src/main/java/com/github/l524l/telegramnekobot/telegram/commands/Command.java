package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command {
    protected UserRoles required_role;
    protected String name;
    protected String description;

    protected Command(Class<? extends Command> command) throws BotException {
        if (command.isAnnotationPresent(TheCommand.class)){
            TheCommand theCommand = command.getAnnotation(TheCommand.class);
            required_role = theCommand.required_role();
            name = theCommand.name();
            description = theCommand.description();
        } else throw new BotException("Class " + command.getName() + " doesn't have annotation TheCommand");
    }

    public abstract void execute(Update update);

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
