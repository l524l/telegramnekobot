package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

@BotCommand
public class Categories extends Command {

    private final TelegramSender telegramSender;
    private final KeyboardTemplate template;

    @Autowired
    public Categories(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.template = templates.getTemplate("chose_category");
    }

    @Override
    public void execute() throws TelegramApiException {

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .chatId(String.valueOf(context.getBotUser().getId()))
                .text("Ты можешь выбрать категорию");

        sendMessageBuilder.replyMarkup(template.getAsKeyboard(InlineKeyboardMarkup.class));

        telegramSender.execute(sendMessageBuilder.build());
    }

    @Override
    public String getName() {
        return "categories";
    }

    @Override
    public String getDescription() {
        return "выводит список доступных категорий";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        Set<String> aliases = new HashSet();

        aliases.add("\uD83D\uDCDD Выбрать категорию");

        return aliases;
    }
}
