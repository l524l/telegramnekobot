package com.github.l524l.telegramnekobot.handlers.impl;

import com.github.l524l.telegramnekobot.handlers.UpdateHandler;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
public class InlineRequestHandler extends UpdateHandler {

    private final TelegramSender telegramSender;
    private final NekosApi nekosApi;
    private final BotSettings settings;
    private final KeyboardTemplate template;

    public InlineRequestHandler(TelegramSender telegramSender, NekosApi nekosApi, BotSettings settings, KeyboardTemplatesStore templates) {
        this.telegramSender = telegramSender;
        this.nekosApi = nekosApi;
        this.settings = settings;
        this.template = templates.getTemplate("neko_category");
    }

    @Override
    public String handleRequest(Update update) {
        if (update.hasInlineQuery()) {
            AnswerInlineQuery answerInlineQuery = AnswerInlineQuery
                    .builder()
                    .inlineQueryId(update.getInlineQuery().getId())
                    .build();

            List<InlineQueryResult> list = new ArrayList();

            nekosApi.getNekoCategoryList().forEach((x)->{
                String name = x.getName();
                String id = UUID.randomUUID().toString();

                template.fillTemplate(name);

                InlineKeyboardMarkup keyboard = template.getAsKeyboard(InlineKeyboardMarkup.class);

                String thumb = settings.getBotPath();
                thumb += x.isNsfw() ? "/adult.png" : "/normal.jpg";

                list.add(InlineQueryResultArticle
                        .builder()
                        .title(name)
                        .id(id)
                        .thumbUrl(thumb)
                        .inputMessageContent(InputTextMessageContent.builder().messageText("picture").build())
                        .replyMarkup(keyboard)
                        .build()
                );

                template.clearTemplate();
            });
            answerInlineQuery.setResults(list);

            try {
                telegramSender.execute(answerInlineQuery);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        return handleByNextHandler(update);
    }
}
