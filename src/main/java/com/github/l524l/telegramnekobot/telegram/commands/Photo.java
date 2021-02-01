package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        try {
            String[] s = update.getMessage().getText().split(" ");
            if (s.length >= 2) {
                String url = nekosApi.execute(s[1]);
                try {
                    if (url.endsWith(".gif")) {
                        SendDocument sendDocument = new SendDocument();
                        sendDocument.setChatId(chat_id);
                        sendDocument.setDocument(new InputFile(url));

                        telegramSender.execute(sendDocument);
                    } else {
                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setChatId(chat_id);
                        sendPhoto.setPhoto(new InputFile(url));
                        telegramSender.execute(sendPhoto);
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chat_id);
                sendMessage.setText("Вы не выбрали категорию.\nВведите /categories для отображения списка категорий");
                telegramSender.execute(sendMessage);
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
