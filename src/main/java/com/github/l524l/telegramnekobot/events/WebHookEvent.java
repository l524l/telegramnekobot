package com.github.l524l.telegramnekobot.events;

import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class WebHookEvent implements ApplicationListener<ContextRefreshedEvent> {

    private final TelegramSender telegramSender;
    private final String currentBotUrl;

    public WebHookEvent(TelegramSender telegramSender, BotSettings settings) {
        this.telegramSender = telegramSender;
        this.currentBotUrl = settings.getBotPath();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            String webHookUrl = telegramSender.getWebhookInfo().getUrl();
            if (!webHookUrl.equals(currentBotUrl)){
                SetWebhook setWebhook = SetWebhook.builder().url(currentBotUrl).build();

                telegramSender.execute(setWebhook);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
