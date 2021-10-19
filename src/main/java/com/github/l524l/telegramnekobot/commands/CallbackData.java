package com.github.l524l.telegramnekobot.commands;

import java.util.List;

public class CallbackData {

    private CommandType type;
    private List<String> parameters;

    public CommandType getType() {
        return type;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
