package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Photo extends Command {
    private static final String COMMAND = "photo";
    private static final String DESCRIPTION = "Отправляет фото согласно категории";
    private static final UserRoles REQUIRED_ROLE = UserRoles.USER;
    private final NekosApi nekosApi;

    @Autowired
    private TelegramSender telegramSender;

    public Photo(NekosApi nekosApi) {
        super(COMMAND, DESCRIPTION, REQUIRED_ROLE);
        this.nekosApi = nekosApi;
    }

    @Override
    public void execute(Update update) {
        try {
            String[] s = update.getMessage().getText().split(" ");
            String url = nekosApi.execute(s[1]);

            try {
                if (url.endsWith(".gif")){
                    SendDocument sendDocument = new SendDocument();
                    sendDocument.setChatId(update.getMessage().getChatId().toString());
                    sendDocument.setDocument(new InputFile(url));

                    telegramSender.execute(sendDocument);
                }else {
                    SendPhoto sendPhoto = new SendPhoto();
                    sendPhoto.setChatId(update.getMessage().getChatId().toString());
                    sendPhoto.setPhoto(new InputFile(url));
                    telegramSender.execute(sendPhoto);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public void setTelegramSender(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }
}
