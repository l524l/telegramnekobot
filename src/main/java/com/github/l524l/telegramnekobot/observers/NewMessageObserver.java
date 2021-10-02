package com.github.l524l.telegramnekobot.observers;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface NewMessageObserver {
    void onNewMessage(BotUser user, Message message);
}
