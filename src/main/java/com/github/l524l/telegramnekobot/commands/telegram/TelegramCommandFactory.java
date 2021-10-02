package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.commands.CommandFactory;
import com.github.l524l.telegramnekobot.commands.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TelegramCommandFactory extends CommandFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public TelegramCommandFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Command createCommand(CommandType type) {
        return (Command) applicationContext.getBean(type.name());
    }
}
