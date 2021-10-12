package com.github.l524l.telegramnekobot.settings;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BotProperty {
    @Id
    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
