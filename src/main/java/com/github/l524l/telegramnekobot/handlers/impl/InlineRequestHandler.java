package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.commands.inline.InlineCommandFactory;
import com.github.l524l.telegramnekobot.exceptions.CommandNotFindException;
import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;


@Component
public class InlineRequestHandler extends UpdateHandler {

    private final InlineCommandFactory commandFactory;

    public InlineRequestHandler(InlineCommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public String handleRequest(Update update) {
        if (update.hasInlineQuery()) {
            try {
                Command command = commandFactory.createCommand(update.getInlineQuery().getQuery());
                command.execute();
            } catch (CommandNotFindException e) {
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                return "ok";
            }
        }
        return handleByNextHandler(update);
    }
}
