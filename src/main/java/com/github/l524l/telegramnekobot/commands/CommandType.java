package com.github.l524l.telegramnekobot.commands;

public enum CommandType {
    DEFAULT_COMMAND("default"),
    ADD_ROLE_COMMAND("addrole"),
    GET_ADMIN_LIST_COMMAND("adminslist"),
    GET_CATEGORIES_COMMAND("categories"),
    GET_USER_STATUS_COMMAND("mystatus"),
    GET_PICTURE_COMMAND("picture"),
    REMOVE_ROLE_COMMAND("removerole"),
    SET_NSFW_MODE_COMMAND("setnsfwmode"),
    BLOCK_NSFW_CONTENT_COMMAND("bloknsfwcontent"),
    START_COMMAND("start");


    private String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public static CommandType getByCommandName(String commandName) {
        for (CommandType type:
             CommandType.values()) {
            if (type.getCommandName().equals(commandName)) return type;
        }
        return DEFAULT_COMMAND;
    }
}
