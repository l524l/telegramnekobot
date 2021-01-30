package com.github.l524l.telegramnekobot.telegram.commands;

import com.github.l524l.telegramnekobot.telegram.UserRoles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //Указывает, что наша Аннотация может быть использована во время выполнения через Reflection (нам как раз это нужно).
@Target(ElementType.TYPE)
public @interface TheCommand {
    String name(); //Команда за которую будет отвечать функция (например "привет");
    String description();
    UserRoles required_role();
}
