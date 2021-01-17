package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.telegram.UserRoles;
import com.github.l524l.telegramnekobot.telegram.apimethod.SendPhotoByURL;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Photo extends Command {
    private static final String COMMAND = "photo";
    private static final String DESCRIPTION = "Отправляет фото согласно категории";
    private static final UserRoles REQUIRED_ROLE = UserRoles.USER;

    public Photo() {
        super(COMMAND, DESCRIPTION, REQUIRED_ROLE);
    }

    @Override
    public BotApiMethod execute(Update update) {
        SendPhotoByURL sendPhoto = new SendPhotoByURL();
        sendPhoto.setChat_id(update.getMessage().getChatId().toString());
        sendPhoto.setPhoto("https://cdn.nekos.life/neko/neko_070.jpg");
        return sendPhoto;
    }
}
