package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Deprecated
public class CommandHandler {
    private final Logger logger = LoggerFactory.getLogger(CommandHandler.class);
    private final List<Command> list;
    private CommandsExecutionProtector protector;

    private final Command default_command;

    public CommandHandler(List<Command> list, CommandsExecutionProtector protector, Command default_command){
        this.list = list;
        this.protector = protector;
        this.default_command = default_command;
    }

    public void execute(Update update){
        try {
            String text = Command.getText(update);
            protector.execute(findCommand(text), update);
        } catch (BotException e) {
            e.printStackTrace();
        }
    }

    private Command findCommand(String text) {
        AtomicReference<Command> command = new AtomicReference<>();
        list.forEach((x) -> {
            if (text.matches(String.format("(/%s)((\\s.+)|$|(\\s))", x.getName()))){
                command.set(x);
            }
        });
        if (command.get() == null) return default_command;
        return command.get();
    }
}
