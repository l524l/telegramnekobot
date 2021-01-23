package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Photo extends Command {
    private static final String COMMAND = "photo";
    private static final String DESCRIPTION = "Отправляет фото согласно категории";
    private static final UserRoles REQUIRED_ROLE = UserRoles.USER;

    public Photo() {
        super(COMMAND, DESCRIPTION, REQUIRED_ROLE);
    }

    @Override
    public void execute(Update update) {

    }
}
