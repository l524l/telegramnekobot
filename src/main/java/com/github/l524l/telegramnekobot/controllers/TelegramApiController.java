package com.github.l524l.telegramnekobot.controllers;

import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@RestController()
public class TelegramApiController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/bot")
    public @ResponseBody String handlerTelegramApi(@RequestBody String body){
        System.out.println(body);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return "TRUE";
    }
}
