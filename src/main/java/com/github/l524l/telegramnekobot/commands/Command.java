package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.exceptions.ValidationException;
import com.github.l524l.telegramnekobot.user.BotUser;
import com.github.l524l.telegramnekobot.validation.Validation;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Command implements Validation {

    protected BotUser executor;
    protected Message message;
    protected List<String> parameters;

    public abstract void execute() throws Exception;

    public void setContext(BotUser executor, Message message) {
        this.executor = executor;
        this.message = message;
        this.parameters = parsParameters();
    }

    public BotUser getExecutor() {
        return executor;
    }

    public Message getMessage() {
        return message;
    }

    protected List<String> parsParameters() {
        try {
            String text = "";
            text = message.getText();
            text = text.replaceFirst("(^/\\S+(\\s+|$))", "");
            if (text.length() > 0) {
                return Arrays.asList(text.split("\\s+"));
            } else {
                return Collections.emptyList();
            }

        }catch (NullPointerException e){
            return Collections.emptyList();
        }

    }

    public List<String> getParams() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void validate() throws ValidationException {
        if (executor == null | message == null) throw new ValidationException("Контекст команды пуст или не полон");
    }
}
