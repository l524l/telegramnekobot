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
public class Settings extends Command {

    private final TelegramSender telegramSender;
    private final KeyboardTemplate templates;

    public Settings(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.templates = templates.getTemplate("settings_keyboard");
    }

    @Override
    public void execute() throws Exception {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .chatId(String.valueOf(context.getBotUser().getId()))
                .text("⚙️ Настройки")
                .replyMarkup(templates.getAsKeyboard(ReplyKeyboardMarkup.class));

        telegramSender.execute(sendMessageBuilder.build());
    }

    @Override
    public String getName() {
        return "settings";
    }

    @Override
    public String getDescription() {
        return "настройки пользователя";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        Set<String> aliases = new HashSet();

        aliases.add("⚙ Настройки");

        return aliases;
    }
}
