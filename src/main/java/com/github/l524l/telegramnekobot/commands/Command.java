package com.github.l524l.telegramnekobot.commands;

import com.github.l524l.telegramnekobot.handlers.RequestContext;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class Command {

    protected String parameter;
    protected RequestContext context;

    public abstract void execute() throws Exception;

    @Autowired
    public void setContext(RequestContext context) {
        this.context = context;
    }

    public RequestContext getContext() {
        return context;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameters(String parameter) {
        this.parameter = parameter;
    }

    abstract public String getName();

    abstract public String getDescription();

    abstract public boolean isHidden();

    abstract public Set<String> getAliases();

}
