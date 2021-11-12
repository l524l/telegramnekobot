package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.net.URL;
import java.util.Collections;
import java.util.Set;

@BotCommand
public class Picture extends Command {

    private final TelegramSender telegramSender;
    private final NekosApi nekosApi;
    private final KeyboardTemplate template;

    @Autowired
    public Picture(TelegramSender telegramSender, NekosApi nekosApi, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.nekosApi = nekosApi;
        this.template = templates.getTemplate("picture_keyboard");
    }

    @Override
    public void execute() throws Exception {

        URL imgUrl = nekosApi.execute(parameter);
        InputFile photoFile = new InputFile(imgUrl.toString());

        String caption = String.format("*%s*", parameter);
        String parseMode = "MarkdownV2";

        String anotherCategory = context.getBotUser().getUserSettings().isNSFW() ? "pictureNsfw": "pictureSfw";

        template.fillTemplate(parameter, anotherCategory);

        ReplyKeyboard keyboard = template.getAsKeyboard(InlineKeyboardMarkup.class);

        
        String userID = String.valueOf(context.getBotUser().getId());

        try {
            if (imgUrl.getFile().toLowerCase().endsWith(".gif")) {
                SendAnimation sendDocument = SendAnimation.builder()
                        .chatId(userID)
                        .animation(photoFile)
                        .parseMode(parseMode)
                        .caption(caption)
                        .replyMarkup(keyboard)
                        .build();
                telegramSender.execute(sendDocument);
            } else {
                SendPhoto sendPhoto = SendPhoto.builder()
                        .chatId(userID)
                        .photo(photoFile)
                        .parseMode(parseMode)
                        .caption(caption)
                        .replyMarkup(keyboard)
                        .build();
                telegramSender.execute(sendPhoto);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            template.clearTemplate();
        }
    }

    @Override
    public String getName() {
        return "picture";
    }

    @Override
    public String getDescription() {
        return "отправляет картинку согласно категории";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        return Collections.emptySet();
    }
}
