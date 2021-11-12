package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.exceptions.CommandNotFindException;

public abstract class CommandFactory {
    public abstract Command createCommand(String type) throws CommandNotFindException;
}
