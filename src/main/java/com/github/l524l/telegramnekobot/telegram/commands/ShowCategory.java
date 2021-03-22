package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.BotUser;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
@TheCommand(name = "showcategory", description = "изменяет текст списка категорий", required_role = UserRoles.USER)
public class ShowCategory extends CallbackCommand {
    protected ShowCategory() throws BotException {
        super(ShowCategory.class);
    }

    @Override
    public void execute(Update update, BotUser executor) throws BotException {
        sendAnswer(getQueryId(update));

        String SFW = "```     SFW\n" +
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
                "     1/2```";
        String NSFW = "```     NSFW\n" +
                "---------------\n" +
                "4k    | lesbian\n" +
                "gasm  | hentai\n" +
                "cum   | blowjob\n" +
                "gif   | boobs\n" +
                "lewd  | pussy\n" +
                "anal  | holo\n" +
                "     2/2```";


        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(getChatID(update))
                .messageId(update.getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(update.getCallbackQuery().getMessage().getReplyMarkup())
                .text("")
                .parseMode("MarkdownV2")
                .build();
        ArrayList<InlineKeyboardButton> arrayList = new ArrayList<>();


        if (getText(update).matches(".+ SFW")){
            editMessageText.setText(SFW);
            arrayList.add(InlineKeyboardButton.builder().text("→").callbackData("/showcategory NSFW").build());
            editMessageText.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
        }

        else if (getText(update).matches(".+ NSFW")) {
            editMessageText.setText(NSFW);
            arrayList.add(InlineKeyboardButton.builder().text("←").callbackData("/showcategory SFW").build());
            editMessageText.setReplyMarkup(InlineKeyboardMarkup.builder().keyboardRow(arrayList).build());
        }


        try {
            telegramSender.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}