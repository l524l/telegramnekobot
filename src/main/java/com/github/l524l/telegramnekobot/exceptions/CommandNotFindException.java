package com.github.l524l.telegramnekobot.exceptions;

public class CommandNotFindException extends BotException {
    public CommandNotFindException() {
        super("Команда не распознана");
    }
}
