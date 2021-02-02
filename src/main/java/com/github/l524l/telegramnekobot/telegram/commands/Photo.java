package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.nekosapi.NekosApiException;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URL;

@Component("photo")
@TheCommand(name = "photo",description = "Отправляет фото согласно категории",required_role = UserRoles.USER)
public class Photo extends Command {

    @Autowired
    private NekosApi nekosApi;

    public Photo() throws BotException {
        super(Photo.class);
    }

    @Override
    public void execute(Update update) {
        String chat_id = update.getMessage().getChatId().toString();
        String[] params = update.getMessage().getText().split(" ");
        try {
            if (params.length >= 2) {

                URL url = null;
                try {
                    url = nekosApi.execute(params[1]);
                } catch (NekosApiException e) {
                    if (e.getCode() == 101) {
                        SendMessage sendMessage = SendMessage
                                .builder()
                                .chatId(chat_id)
                                .text("Такая категория отсутствует\nВведите /categories для отображения списка категорий")
                                .build();
                        telegramSender.execute(sendMessage);
                        return;
                    }
                }

                if (url.getFile().endsWith(".gif") | url.getFile().endsWith(".GIF")) {
                    SendDocument sendDocument = SendDocument
                            .builder()
                            .chatId(chat_id)
                            .document(new InputFile(url.toString()))
                            .build();
                    telegramSender.execute(sendDocument);
                } else {
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .chatId(chat_id)
                            .photo(new InputFile(url.toString()))
                            .build();
                    telegramSender.execute(sendPhoto);
                }
            } else {
                SendMessage sendMessage = SendMessage
                        .builder()
                        .chatId(chat_id)
                        .text("Вы не выбрали категорию.\nВведите /categories для отображения списка категорий")
                        .build();
                telegramSender.execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
