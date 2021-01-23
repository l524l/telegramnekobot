package com.github.l524l.telegramnekobot.nekosapi;

public class NekoCategory {
    private final String name;
    private final Boolean nsfw;

    public NekoCategory(String name, Boolean nsfw) {
        this.name = name;
        this.nsfw = nsfw;
    }

    public String getName() {
        return name;
    }

    public Boolean isNsfw() {
        return nsfw;
    }
}
