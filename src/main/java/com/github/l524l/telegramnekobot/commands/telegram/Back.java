package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.HashSet;
import java.util.Set;

@BotCommand
public class Back extends Command {

    private final TelegramSender telegramSender;
    private final KeyboardTemplate template;

    public Back(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.template = templates.getTemplate("default_keyboard");
    }

    @Override
    public void execute() throws Exception {

        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .chatId(String.valueOf(context.getBotUser().getId()))
                .text("\uD83D\uDD19 Назад")
                .replyMarkup(template.getAsKeyboard(ReplyKeyboardMarkup.class));

        telegramSender.execute(sendMessageBuilder.build());
    }

    @Override
    public String getName() {
        return "back";
    }

    @Override
    public String getDescription() {
        return "назад";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        Set<String> aliases = new HashSet();

        aliases.add("\uD83D\uDD19 Назад");

        return aliases;
    }
}
