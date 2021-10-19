package com.github.l524l.telegramnekobot.observers;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface NewCallbackObserver {
    void onNewCallback(BotUser botUser, CallbackQuery callback);
}
