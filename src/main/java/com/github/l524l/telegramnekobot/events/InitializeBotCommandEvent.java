package com.github.l524l.telegramnekobot.events;

import com.github.l524l.telegramnekobot.commands.CommandType;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeBotCommandEvent implements ApplicationListener<ContextRefreshedEvent> {

    private final TelegramSender telegramSender;

    public InitializeBotCommandEvent(TelegramSender telegramSender) {
        this.telegramSender = telegramSender;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<BotCommand> commands = new ArrayList<>();
        SetMyCommands setMyCommands = new SetMyCommands();
        for (CommandType type :
                CommandType.values()) {
            if (type.isHidden()) continue;

            String name = type.getCommandName();
            String description = type.getDescription();


            commands.add(BotCommand.builder().command(name).description(description).build());
        }
        setMyCommands.setCommands(commands);

        try {
            telegramSender.execute(setMyCommands);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
