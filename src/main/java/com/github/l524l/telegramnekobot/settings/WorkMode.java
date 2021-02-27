package com.github.l524l.telegramnekobot.settings;

public enum WorkMode {
    SFW(false), NSFW(true);

    private boolean nsfw;

    WorkMode(boolean b) {
        nsfw = b;
    }

    public boolean isNsfw() {
        return nsfw;
    }
}
