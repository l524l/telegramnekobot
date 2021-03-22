package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
@TheCommand(name = "categories", description = "Отображает список категорий", required_role = UserRoles.USER)
public class Categories extends Command {
    public Categories() throws BotException {
        super(Categories.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(getChatID(update))
                .text(("```     SFW\n" +
                        "------------------\n" +
                        "lick  | kiss\n" +
                        "hug   | wallpapers\n" +
                        "baka  | tickle\n" +
                        "feet  | animalears\n" +
                        "pat   | laugh\n" +
                        "feed  | cuddle\n" +
                        "poke  | smug\n" +
                        "slap  | foxgirl\n" +
                        "neko  |\n" +
                        "     1/2```"))
                .parseMode("MarkdownV2")
                .build();
        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
        arrayList.add(InlineKeyboardButton.builder().text("→").callbackData("/showcategory NSFW").build());
        sendMessage.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());

        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
