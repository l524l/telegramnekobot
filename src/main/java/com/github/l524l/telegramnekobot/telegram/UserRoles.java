package com.github.l524l.telegramnekobot.telegram;

@Deprecated
public enum  UserRoles {
    ADMIN(1), USER(2), OWNER(0);

    private int priority;

    UserRoles(int priority) {
        this.priority = priority;
    }

    public Integer getPriority(){
        return priority;
    }

    public boolean isLessPriority(UserRoles role){
        return role.getPriority() <= priority;
    }
}
