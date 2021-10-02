package com.github.l524l.telegramnekobot.observers;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface NewMessageSubject {
    void registerObserver(NewMessageObserver observer);
    void removeObserver(NewMessageObserver observer);
    void notifyObservers(BotUser sender, Message message);
}
