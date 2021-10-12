package com.github.l524l.telegramnekobot.settings;

public class BotSettings {

    private String botToken;
    private String botUsername;
    private String botPath;

    public BotSettings() {
    }

    public BotSettings(String botToken, String botUsername, String botPath) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.botPath = botPath;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

}
