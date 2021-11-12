package com.github.l524l.telegramnekobot.commands.inline;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;
import com.github.l524l.telegramnekobot.settings.BotSettings;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.template.KeyboardTemplate;
import com.github.l524l.telegramnekobot.template.KeyboardTemplatesStore;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@BotCommand
public class PictureSFW extends InlineCommand {
    private final NekosApi nekosApi;
    private final KeyboardTemplate template;
    private final BotSettings settings;
    private final TelegramSender telegramSender;

    public PictureSFW(NekosApi nekosApi, KeyboardTemplatesStore templatesStore, BotSettings settings, TelegramSender telegramSender) {
        this.nekosApi = nekosApi;
        this.template = templatesStore.getTemplate("neko_category");
        this.settings = settings;
        this.telegramSender = telegramSender;
    }

    @Override
    public void execute() {
        AnswerInlineQuery answerInlineQuery = AnswerInlineQuery
                .builder()
                .inlineQueryId(context.getUpdate().getInlineQuery().getId())
                .build();

        List<InlineQueryResult> list = new ArrayList();

        nekosApi.getNekoCategoryList().forEach((x)->{
            if(!x.isNsfw()) {
                String name = x.getName();
                String id = UUID.randomUUID().toString();

                template.fillTemplate(name);

                InlineKeyboardMarkup keyboard = template.getAsKeyboard(InlineKeyboardMarkup.class);

                String thumb = settings.getBotPath() + "/normal.jpg";

                list.add(InlineQueryResultArticle
                        .builder()
                        .title(name)
                        .id(id)
                        .thumbUrl(thumb)
                        .inputMessageContent(InputTextMessageContent.builder().messageText("picture").build())
                        .replyMarkup(keyboard)
                        .build());
                template.clearTemplate();
            }
        });
        answerInlineQuery.setResults(list);

        try {
            telegramSender.execute(answerInlineQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "pictureSfw";
    }
}
