package com.github.l524l.telegramnekobot.commands;

public enum CommandType {
    DEFAULT_COMMAND("default", "", true),
    START_COMMAND("start", "", true),
    GET_PICTURE_COMMAND("picture", "отправляет картинку согласно категории", false),
    GET_CATEGORIES_COMMAND("categories", "выводит список доступных категорий", false),
    GET_USER_STATUS_COMMAND("mystatus", "выводит список ваших ролей", false),
    ADD_ROLE_COMMAND("addrole", "добавляет роль пользователю", false),
    REMOVE_ROLE_COMMAND("removerole", "удаляет роль пользователя", false),
    GET_ADMIN_LIST_COMMAND("adminslist", "показывает список администраторов", false),
    SET_NSFW_MODE_COMMAND("setnsfwmode", "изменяет возможность просмотра NSFW контента", true),
    BLOCK_NSFW_CONTENT_COMMAND("bloknsfwcontent", "глобальная настройка NSFW контента", true);


    private String commandName;
    private String description;
    private boolean isHidden;

    CommandType(String commandName, String description, boolean isHidden) {
        this.commandName = commandName;
        this.description = description;
        this.isHidden = isHidden;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHidden() {
        return isHidden;
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
