package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.telegram.commands.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class CommandHandler {
    private final List<Command> list;
    public CommandHandler(List<Command> list){

        this.list = list;
    }

    public void execute(Update update){
        list.forEach((x) -> {
            if (update.getMessage().getText().contains(x.getName())){
                x.execute(update);
            }
        });
    }
}
