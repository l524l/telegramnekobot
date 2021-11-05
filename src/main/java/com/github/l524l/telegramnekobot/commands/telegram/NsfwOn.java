package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Collections;
import java.util.Set;

@BotCommand
public class NsfwOn extends Command {

    private final TelegramSender telegramSender;
    private final UserRepository repository;
    private final KeyboardTemplate template;

    public NsfwOn(TelegramSender telegramSender, UserRepository repository, KeyboardTemplatesStore templatesStore) {
        this.telegramSender = telegramSender;
        this.repository = repository;
        this.template = templatesStore.getTemplate("nsfw_on");
    }


    @Override
    public void execute() throws Exception {
        BotUser user = context.getBotUser();
        EditMessageReplyMarkup editMessage = EditMessageReplyMarkup.builder()
                .chatId(String.valueOf(user.getId()))
                .messageId(context.getUpdate().getCallbackQuery().getMessage().getMessageId())
                .replyMarkup(template.getAsKeyboard(InlineKeyboardMarkup.class))
                .build();


        user.getUserSettings().setNSFW(true);

        repository.save(user);

        telegramSender.execute(editMessage);
    }

    @Override
    public String getName() {
        return "nsfw_on";
    }

    @Override
    public String getDescription() {
        return "";
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
