package com.github.l524l.telegramnekobot.handlers;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequestScope
public class RequestContext {
    private BotUser botUser;
    private Update update;

    public BotUser getBotUser() {
        return botUser;
    }

    public void setBotUser(BotUser botUser) {
        this.botUser = botUser;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
