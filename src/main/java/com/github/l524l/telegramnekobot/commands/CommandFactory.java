package com.github.l524l.telegramnekobot.commands;

public abstract class CommandFactory {
    public abstract Command createCommand(String type);
}
