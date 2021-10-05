package com.github.l524l.telegramnekobot.user;

public enum UserRole {
    OWNER,
    ADMIN,
    USER;

    public static boolean isExist(String role) {
        try {
            UserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
