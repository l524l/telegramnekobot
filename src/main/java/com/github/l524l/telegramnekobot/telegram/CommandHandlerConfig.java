package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.telegram.commands.Categories;
import com.github.l524l.telegramnekobot.telegram.commands.Command;
import com.github.l524l.telegramnekobot.telegram.commands.Default;
import com.github.l524l.telegramnekobot.telegram.commands.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CommandHandlerConfig {
    @Autowired
    private Photo photo;
    @Autowired
    private Categories categories;
    @Autowired
    private Default default_command;
    @Autowired
    private CommandsExecutionProtector protector;

    @Bean
    public CommandHandler createCommandHandler(){
        List<Command> commands = new ArrayList<>();
        commands.add(photo);
        commands.add(categories);

        CommandHandler commandHandler = new CommandHandler(commands, protector, default_command);
        return commandHandler;
    }
}
