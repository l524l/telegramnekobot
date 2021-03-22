package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@TheCommand(name = "another", description = "Еще одно фото такого же типа", required_role = UserRoles.USER)
public class AnotherPhoto extends CallbackCommand {

    private final Photo photo;

    protected AnotherPhoto(Photo photo) throws BotException {
        super(AnotherPhoto.class);
        this.photo = photo;
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        sendAnswer(getQueryId(update));
        photo.execute(update, executor);
    }
}