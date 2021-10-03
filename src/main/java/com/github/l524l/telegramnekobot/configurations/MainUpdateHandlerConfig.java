package com.github.l524l.telegramnekobot.configurations;

import com.github.l524l.telegramnekobot.commands.CommandQualifier;
import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.handlers.UpdateHandlersChainBuilder;
import com.github.l524l.telegramnekobot.handlers.impl.NewMessageUpdateHandler;
import com.github.l524l.telegramnekobot.handlers.impl.UserUpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainUpdateHandlerConfig {

    @Autowired
    private UpdateHandlersChainBuilder builder;
    @Autowired
    private UserUpdateHandler userUpdateHandler;
    @Autowired
    private NewMessageUpdateHandler newMessageHandler;
    @Autowired
    private CommandQualifier qualifier;


    @Bean(name = "mainHandler")
    public UpdateHandler createMainRequestHandler() {
        newMessageHandler.registerObserver(qualifier);

        builder.addHandler(userUpdateHandler);
        builder.addHandler(newMessageHandler);

        return builder.buildChain();
    }
}
