package com.github.l524l.telegramnekobot.handlers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class UpdateHandlersChainBuilder {

    private List<UpdateHandler> handlers;

    public UpdateHandlersChainBuilder() {
        handlers = new ArrayList<>();
    }

    public UpdateHandler buildChain() {
        for (int i = handlers.size()-1; i > 0 ; i--) {
            handlers.get(i-1).setNextHandler(handlers.get(i));
        }
        return handlers.get(0);
    }

    public void addHandler(UpdateHandler handler) {
        handlers.add(handler);
    }

}
