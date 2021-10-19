package com.github.l524l.telegramnekobot.observers;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface NewCallbackSubject {
    void registerObserver(NewCallbackObserver observer);
    void removeObserver(NewMessageObserver observer);
    void notifyObservers(BotUser sender, CallbackQuery callback);
}
