package com.github.l524l.telegramnekobot.telegram.apimethod;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

public class SendPhotoByURL extends BotApiMethod<Message> {

    private String chat_id;
    private String photo;
    private final String method = "sendPhoto";

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public Message deserializeResponse(String answer) throws TelegramApiRequestException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void validate() throws TelegramApiValidationException {
        if (chat_id == null) throw new TelegramApiValidationException("ChatId parameter can't be empty", this);
        if (photo == null) throw new TelegramApiValidationException("Photo parameter can't be empty", this);
    }
}
