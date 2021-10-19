package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@BotCommand("GET_CATEGORIES_COMMAND")
public class Categories extends Command {

    private final TelegramSender telegramSender;

    @Autowired
    public Categories(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }

    @Override
    public void execute() throws TelegramApiException {
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(message.getChatId().toString())
                .text(("```     SFW\n" +
                        "------------------\n" +
                        "lick  | kiss\n" +
                        "hug   | wallpapers\n" +
                        "baka  | tickle\n" +
                        "neko  | animalears\n" +
                        "pat   | laugh\n" +
                        "feed  | cuddle\n" +
                        "poke  | smug\n" +
                        "slap  | foxgirl\n" +
                        "     1/2```"))
                .parseMode("MarkdownV2")
                .build();

        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
        String data = "{\"type\":\"SCROLL_CATEGORY\",\"parameters\":[\"nsfw\"]}";
        arrayList.add(InlineKeyboardButton.builder().text("→").callbackData(data).build());
        sendMessage.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());

        telegramSender.execute(sendMessage);
    }
}
