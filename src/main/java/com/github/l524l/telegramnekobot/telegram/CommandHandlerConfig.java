package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.telegram.commands.Command;
import com.github.l524l.telegramnekobot.telegram.commands.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Deprecated
public class CommandHandlerConfig {
    @Autowired
    private List<Command> commands;
    @Autowired
    private Default default_command;
    @Autowired
    private CommandsExecutionProtector protector;

    @Bean
    public CommandHandler createCommandHandler(){
        CommandHandler commandHandler = new CommandHandler(commands, protector, default_command);
        return commandHandler;
    }
}
