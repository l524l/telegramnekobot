package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.CommandExecutor;
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
    public void execute(Update update, CommandExecutor executor) {
        SendMessage sendMessage = SendMessage.
                builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(("```\n" +
                    "nsfw    | sfw\n" +
                    "---------------------\n"+
                    "4k      | neko\n" +
                    "gasm    | kiss\n" +
                    "cum     | lick\n" +
                    "gif     | hug\n" +
                    "lewd    | wallpapers\n" +
                    "anal    | tickle\n" +
                    "hentai  | baka\n" +
                    "lesbian | animalears\n" +
                    "blowjob | laugh\n" +
                    "holo    | feet\n" +
                    "pussy   | cuddle\n" +
                    "boobs   | pat\n" +
                    "        | feed\n" +
                    "        | foxgirl\n" +
                    "        | slap\n" +
                    "        | poke\n" +
                    "        | smug\n```"))
                .parseMode("MarkdownV2")
                .build();
        try {
            telegramSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
