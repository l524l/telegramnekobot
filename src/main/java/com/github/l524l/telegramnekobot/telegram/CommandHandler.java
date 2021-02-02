package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.telegram.commands.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CommandHandler {
    private final List<Command> list;

    private final Command default_command;

    public CommandHandler(List<Command> list, Command default_command){
        this.list = list;
        this.default_command = default_command;
    }

    public void execute(Update update){
        String text = update.getMessage().getText();
        try {
            findCommand(text).execute(update);
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
