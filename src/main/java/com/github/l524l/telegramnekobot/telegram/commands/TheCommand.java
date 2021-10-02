package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.telegram.UserRoles;

import java.lang.annotation.*;

/**
 * Данная аннотация указывает то, что аннотируемый класс является командой бота.
 * Является обязательной для всех наследников класса Command и содержит базовое описание команды.
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TheCommand {
    String name();
    String description();
    UserRoles required_role();
}
