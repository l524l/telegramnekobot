package com.github.l524l.telegramnekobot.commands.inline;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.commands.CommandFactory;
import com.github.l524l.telegramnekobot.exceptions.CommandNotFindException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InlineCommandFactory extends CommandFactory {

    private final ApplicationContext applicationContext;

    @Autowired
    public InlineCommandFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Command createCommand(String type) throws CommandNotFindException {
        Map<String, InlineCommand> map = applicationContext.getBeansOfType(InlineCommand.class);

        for (Map.Entry<String, InlineCommand> entry: map.entrySet()) {
            Command var = entry.getValue();

            if (var.getName().equals(type) || var.getAliases().contains(type)) return entry.getValue();
        }
        throw new CommandNotFindException();
    }
}
