package com.github.l524l.telegramnekobot.controllers;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebHookController {
    private final UpdateHandler updateHandler;

    @Autowired
    public WebHookController(@Qualifier("mainHandler") UpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public @ResponseBody Object getWebHooks(@RequestBody Update update){
        try {
            return updateHandler.handleRequest(update);
        } catch (Exception e) {
            e.printStackTrace();
            return "ок";
        }
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public @ResponseBody Object getWebHooks(){
        return "ok";
    }
}
