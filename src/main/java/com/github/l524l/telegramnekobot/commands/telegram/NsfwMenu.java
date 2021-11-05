package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashSet;
import java.util.Set;

@BotCommand
public class NsfwMenu extends Command {
    private final TelegramSender telegramSender;
    private final KeyboardTemplate templateOn;
    private final KeyboardTemplate templateOff;

    public NsfwMenu(TelegramSender telegramSender, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.templateOn = templates.getTemplate("nsfw_on");
        this.templateOff = templates.getTemplate("nsfw_off");
    }

    @Override
    public void execute() throws Exception {
        SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                .chatId(String.valueOf(context.getBotUser().getId()))
                .text("18+ контент:");

        if (context.getBotUser().getUserSettings().isNSFW()) {
            sendMessageBuilder.replyMarkup(templateOn.getAsKeyboard(InlineKeyboardMarkup.class));
        } else {
            sendMessageBuilder.replyMarkup(templateOff.getAsKeyboard(InlineKeyboardMarkup.class));
        }

        telegramSender.execute(sendMessageBuilder.build());
    }

    @Override
    public String getName() {
        return "nsfwMenu";
    }

    @Override
    public String getDescription() {
        return "настройка nsfw";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        Set<String> aliases = new HashSet();

        aliases.add("\uD83D\uDD1E 18+ контент");
        return aliases;
    }
}
