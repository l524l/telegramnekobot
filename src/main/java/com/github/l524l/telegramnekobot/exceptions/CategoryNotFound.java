package com.github.l524l.telegramnekobot.exceptions;

import com.github.l524l.telegramnekobot.nekosapi.NekoCategory;

public class CategoryNotFound extends BotRuntimeException {

    public CategoryNotFound(String message) {
        super(message);
    }

    public CategoryNotFound(NekoCategory category) {
        super(String.format("Категория %s не найдена", category.getName()));
    }
}
