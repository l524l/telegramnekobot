package com.github.l524l.telegramnekobot.nekosapi;

public class NekoCategory {
    private String name;
    private Boolean nsfw;

    public NekoCategory() {
    }

    public NekoCategory(String name, Boolean nsfw) {
        this.name = name;
        this.nsfw = nsfw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getName() {
        return name;
    }

    public Boolean isNsfw() {
        return nsfw;
    }
}
