package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Command {

    protected BotUser executor;
    protected Message message;

    public abstract void execute();

    public void setContext(BotUser executor, Message message) {
        this.executor = executor;
        this.message = message;

    }

    public BotUser getExecutor() {
        return executor;
    }

    public Message getMessage() {
        return message;
    }

    protected List<String> getParams() {
        String text = "";
        text = message.getText();
        text = text.replaceFirst("(^/\\S+(\\s+|$))", "");
        if (text.length() > 0) {
            return Arrays.asList(text.split("\\s+"));
        } else {
            return Collections.emptyList();
        }
    }


    
}
