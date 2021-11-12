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
    private final KeyboardTemplate template_sfw;
    private final KeyboardTemplate template_nsfw;

    @Autowired
    public Categories(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.template_sfw = templates.getTemplate("chose_category_sfw");
        this.template_nsfw = templates.getTemplate("chose_category_nsfw");
    }

    @Override
    public void execute() throws TelegramApiException {

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .chatId(String.valueOf(context.getBotUser().getId()))
                .text("Ты можешь выбрать категорию");

        if (context.getBotUser().getUserSettings().isNSFW()) {
            sendMessageBuilder.replyMarkup(template_nsfw.getAsKeyboard(InlineKeyboardMarkup.class));
        }else {
            sendMessageBuilder.replyMarkup(template_sfw.getAsKeyboard(InlineKeyboardMarkup.class));
        }

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
