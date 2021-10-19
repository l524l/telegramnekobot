package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.exceptions.ValidationException;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.net.URL;
import java.util.ArrayList;

@BotCommand("GET_PICTURE_COMMAND")
public class Picture extends Command {

    private final TelegramSender telegramSender;
    private final NekosApi nekosApi;

    @Autowired
    public Picture(TelegramSender telegramSender, NekosApi nekosApi) {
        this.telegramSender = telegramSender;
        this.nekosApi = nekosApi;
    }

    @Override
    public void execute() throws Exception {
        String category = parameters.get(0);

        URL imgUrl = nekosApi.execute(category);

        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
        String data = String.format("{\"type\":\"GET_PICTURE_COMMAND\",\"parameters\":[\"%s\"]}",category);
        arrayList.add(InlineKeyboardButton.builder().text("Ещё " + category).callbackData(data).build());

        if (imgUrl.getFile().endsWith(".gif") | imgUrl.getFile().endsWith(".GIF")) {
            SendAnimation sendDocument = SendAnimation.builder()
                    .chatId(message.getChatId().toString())
                    .animation(new InputFile(imgUrl.toString()))
                    .build();
            sendDocument.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
            telegramSender.execute(sendDocument);
        } else {
            SendPhoto sendPhoto = SendPhoto.builder()
                    .chatId(message.getChatId().toString())
                    .photo(new InputFile(imgUrl.toString()))
                    .build();
            sendPhoto.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
            telegramSender.execute(sendPhoto);
        }

    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (parameters.size() != 1)
            throw new ValidationException("Ожидался параметр:\n/picture {category}\n/categories - для отображения категорий");
    }
}
