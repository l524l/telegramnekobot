package com.github.l524l.telegramnekobot.telegram;

@Deprecated
public class BotUser {
    private final String username;
    private final UserRoles role;

    public BotUser(String username, UserRoles role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public UserRoles getRole() {
        return role;
    }
}
