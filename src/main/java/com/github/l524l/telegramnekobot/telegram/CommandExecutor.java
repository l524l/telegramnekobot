package com.github.l524l.telegramnekobot.telegram;

public class CommandExecutor {
    private final String username;
    private final UserRoles role;

    public CommandExecutor(String username, UserRoles role) {
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
