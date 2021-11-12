package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;
import java.util.Set;

@BotCommand
public class Start extends Command {

    private final TelegramSender telegramSender;
    private final KeyboardTemplatesStore templates;

    @Autowired
    public Start(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.templates = templates;
    }

    @Override
    public void execute() throws TelegramApiException {
        String userID = String.valueOf(context.getBotUser().getId());

        SendMessage message1 = SendMessage.builder()
                .chatId(userID)
                .text("Привет! Развлечемся?!")
                .replyMarkup(templates.getTemplate("default_keyboard").getAsKeyboard(ReplyKeyboardMarkup.class))
                .build();
        SendMessage message2 = SendMessage.builder()
                .chatId(userID)
                .text("Ты можешь выбрать категорию")
                .replyMarkup(templates.getTemplate("chose_category_sfw").getAsKeyboard(InlineKeyboardMarkup.class))
                .build();

        telegramSender.execute(message1);
        telegramSender.execute(message2);
    }

    @Override
    public String getName() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "начать";
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
