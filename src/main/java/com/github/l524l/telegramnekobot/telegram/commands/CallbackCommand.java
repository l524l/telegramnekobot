package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public abstract class CallbackCommand extends Command {
    protected CallbackCommand(Class<? extends Command> command) throws BotException {
        super(command);
    }
    protected void sendAnswer(String queryId, String text, Boolean showAlert){
        CallbackQuery s = new CallbackQuery();
        AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
                .callbackQueryId(queryId)
                .text(text)
                .showAlert(showAlert)
                .build();
        try {
            telegramSender.execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    protected void sendAnswer(String queryId, String text){
        sendAnswer(queryId, text,false);
    }
    protected void sendAnswer(String queryId){
        sendAnswer(queryId, null);
    }

    protected String getQueryId(Update update){
        return update.getCallbackQuery().getId();
    }
}
