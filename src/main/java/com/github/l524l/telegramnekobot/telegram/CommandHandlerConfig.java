package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.telegram.commands.*;
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
    private MyStatus status;
    @Autowired
    private SetWorkMode setWorkMode;
    @Autowired
    private AddAdmin addAdmin;
    @Autowired
    private RemoveAdmin removeAdmin;
    @Autowired
    private AdminsList adminsList;

    @Autowired
    private CommandsExecutionProtector protector;

    @Bean
    public CommandHandler createCommandHandler(){
        List<Command> commands = new ArrayList<>();
        commands.add(photo);
        commands.add(categories);
        commands.add(status);
        commands.add(setWorkMode);
        commands.add(addAdmin);
        commands.add(removeAdmin);
        commands.add(adminsList);

        CommandHandler commandHandler = new CommandHandler(commands, protector, default_command);
        return commandHandler;
    }
}
