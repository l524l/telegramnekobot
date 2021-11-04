package com.github.l524l.telegramnekobot.events;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.DeleteMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class InitializeBotCommandEvent implements ApplicationListener<ContextRefreshedEvent> {

    private final TelegramSender telegramSender;
    private final ApplicationContext context;

    public InitializeBotCommandEvent(TelegramSender telegramSender, ApplicationContext context) {
        this.telegramSender = telegramSender;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<BotCommand> commands = new ArrayList<>();
        SetMyCommands setMyCommands = new SetMyCommands();

        for (Map.Entry<String, Command> entry :
                context.getBeansOfType(Command.class).entrySet()) {

            if (entry.getValue().isHidden()) continue;

            String name = entry.getValue().getName();
            String description = entry.getValue().getDescription();

            commands.add(BotCommand.builder().command(name).description(description).build());
        }
        try {
        if (commands.isEmpty()) {
            telegramSender.execute(new DeleteMyCommands());
            return;
        }
        setMyCommands.setCommands(commands);
            telegramSender.execute(setMyCommands);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
