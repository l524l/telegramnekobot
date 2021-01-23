package com.github.l524l.telegramnekobot.controllers;

import com.github.l524l.telegramnekobot.telegram.TelegramWebHookBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RestController
public class WebHookController {
    private final TelegramWebHookBot telegramWebHookBot;


    @Autowired
    public WebHookController(TelegramWebHookBot telegramWebHookBot) throws TelegramApiException { this.telegramWebHookBot = telegramWebHookBot;
    }

    @RequestMapping(path = "/")
    public @ResponseBody Object getWebHooks(@RequestBody Update update){
        telegramWebHookBot.onWebhookUpdateReceived(update);
        return "ok";
    }
}
