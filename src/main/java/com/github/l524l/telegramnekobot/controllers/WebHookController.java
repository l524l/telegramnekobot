package com.github.l524l.telegramnekobot.controllers;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final UpdateHandler updateHandler;

    @Autowired
    public WebHookController(@Qualifier("mainHandler") UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    @RequestMapping(path = "/")
    public @ResponseBody Object getWebHooks(@RequestBody Update update){
        return updateHandler.handleRequest(update);
    }
}
