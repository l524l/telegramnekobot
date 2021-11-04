package com.github.l524l.telegramnekobot.template;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class KeyboardTemplate {

    private String template;
    private String unFilledTemplate;
    private final Gson gson;

    public KeyboardTemplate(String template) {
        this.template = template;
        this.gson = new Gson();

    }

    public void fillTemplate(String... params) {
        if (unFilledTemplate != null) throw new RuntimeException();

        unFilledTemplate = template;
        template = String.format(template, params);
    }

    public void clearTemplate() {
        if (unFilledTemplate == null) throw new RuntimeException();

        template = unFilledTemplate;
        unFilledTemplate = null;
    }

    public <T extends ReplyKeyboard> T getAsKeyboard(Class<T> cclass) {
            return gson.fromJson(template, cclass);
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
