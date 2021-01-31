package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.UserRoles;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@TheCommand(name = "categories", description = "Отображает список категорий", required_role = UserRoles.USER)
public class Categories extends Command {
    public Categories() throws BotException {
        super(Categories.class);
    }

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Категории:\n" +
                "nsfw       | sfw\n" +
                "-------------------\n"+
                "4k         | neko\n" +
                "bellevid   | kiss\n" +
                "cum        | lick\n" +
                "gif        | hug\n" +
                "spank      |\n" +
                "anal       | tickle\n" +
                "bj         | wallpapers\n" +
                "feed       |\n" +
                "hentai     | baka\n" +
                "lesbian    |\n" +
                "poke       |\n" +
                "animalears |\n" +
                "blowjob    | laugh\n" +
                "feet       |\n" +
                "holo       | foxgirl\n" +
                "lewd       | waifu\n" +
                "pussy      | cuddle\n" +
                "boobs      | pat\n" +
                "slap       |\n" +
                "belle      |\n" +
                "gasm       | smug"
        );
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
