package com.github.l524l.telegramnekobot.annotations;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
@Scope(scopeName = "prototype")
public @interface BotCommand {
    @AliasFor(annotation = Component.class)
    String value();
}
