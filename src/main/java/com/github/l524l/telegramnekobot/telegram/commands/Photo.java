package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.nekosapi.NekosApiException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

@Component("photo")
@TheCommand(name = "photo",description = "Отправляет фото согласно категории",required_role = UserRoles.USER)
public class Photo extends Command {

    @Autowired
    private NekosApi nekosApi;

    public Photo() throws BotException {
        super(Photo.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        String chat_id = getChatID(update);
        String[] params = readParams(update);
        try {
            if (params.length >= 1) {
                URL url = null;
                String category = params[0];
                try {
                    if (UserRoles.ADMIN.isLessPriority(executor.getRole()))
                        url = nekosApi.execute(category, true);
                    else url = nekosApi.execute(category);
                } catch (NekosApiException e) {
                    if (e.getCode() == 101) {
                        SendMessage sendMessage = SendMessage
                                .builder()
                                .chatId(chat_id)
                                .text("Такая категория отсутствует\nВведите /categories для отображения списка категорий")
                                .build();
                        telegramSender.execute(sendMessage);
                        return;
                    } else if (e.getCode() == 103){
                        ClassPathResource resource = new ClassPathResource("/img/404.png");
                        SendPhoto sendPhoto = SendPhoto.builder()
                                .chatId(chat_id)
                                .photo(new InputFile(resource.getInputStream(), "404"))
                                .build();
                        telegramSender.execute(sendPhoto);
                        return;
                    }
                }

                ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
                arrayList.add(InlineKeyboardButton.builder().text("Ещё " + category).callbackData("/another " + category).build());

                if (url.getFile().endsWith(".gif") | url.getFile().endsWith(".GIF")) {
                    SendDocument sendDocument = SendDocument
                            .builder()
                            .chatId(chat_id)
                            .document(new InputFile(url.toString()))
                            .build();
                    sendDocument.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
                    telegramSender.execute(sendDocument);
                } else {
                    SendPhoto sendPhoto = SendPhoto.builder()
                            .chatId(chat_id)
                            .photo(new InputFile(url.toString()))
                            .build();
                    sendPhoto.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
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
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
