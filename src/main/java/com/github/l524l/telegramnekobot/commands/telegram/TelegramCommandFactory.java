package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.commands.CommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TelegramCommandFactory extends CommandFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public TelegramCommandFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Command createCommand(String type) {
        Map<String, Command> map = applicationContext.getBeansOfType(Command.class);

        for (Map.Entry<String,Command> entry: map.entrySet()) {
            Command var = entry.getValue();

            if (var.getName().equals(type) || var.getAliases().contains(type)) return entry.getValue();
        }
        throw new RuntimeException();
    }
}
