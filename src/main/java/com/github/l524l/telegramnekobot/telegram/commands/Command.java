package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

public abstract class Command extends BotCommand {
    protected final UserRoles required_role;

    protected Command(String command, String description, UserRoles required_role) {
        super(command, description);
        this.required_role = required_role;
    }

    public abstract BotApiMethod execute(Update update);

    public UserRoles getRequiredRole(){
        return required_role;
    }
}
