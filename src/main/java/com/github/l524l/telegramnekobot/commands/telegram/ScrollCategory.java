package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;

@BotCommand("SCROLL_CATEGORY")
public class ScrollCategory extends Command {

    private final TelegramSender sender;

    public ScrollCategory(TelegramSender sender) {
        this.sender = sender;
    }

    @Override
    public void execute() throws Exception {
        String SFW = "```     SFW\n" +
                "------------------\n" +
                "lick  | kiss\n" +
                "hug   | wallpapers\n" +
                "baka  | tickle\n" +
                "neko  | animalears\n" +
                "pat   | laugh\n" +
                "feed  | cuddle\n" +
                "poke  | smug\n" +
                "slap  | foxgirl\n" +
                "     1/2```";
        String NSFW = "```     NSFW\n" +
                "---------------\n" +
                "4k    | lesbian\n" +
                "gasm  | hentai\n" +
                "cum   | blowjob\n" +
                "gif   | boobs\n" +
                "lewd  | pussy\n" +
                "anal  | holo\n" +
                "feet  |\n" +
                "     2/2```";

        EditMessageText.EditMessageTextBuilder editMessageText = EditMessageText.builder()
                .chatId(message.getChatId().toString())
                .messageId(message.getMessageId())
                .parseMode("MarkdownV2");

        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();
        String data = "{\"type\":\"SCROLL_CATEGORY\",\"parameters\":[\"%s\"]}";
        switch (parameters.get(0)) {
            case "nsfw":
                editMessageText.text(NSFW);
                arrayList.add(InlineKeyboardButton.builder().text("←").callbackData(String.format(data,"sfw")).build());
                editMessageText.replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
                break;
            case "sfw":
                editMessageText.text(SFW);
                arrayList.add(InlineKeyboardButton.builder().text("→").callbackData(String.format(data,"nsfw")).build());
                editMessageText.replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
                break;
        }
        sender.execute(editMessageText.build());
    }
}
