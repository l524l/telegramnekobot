package com.github.l524l.telegramnekobot.user;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID settingsUUID;

    @ColumnDefault("false")
    @Column(name = "is_nsfw")
    private boolean NSFW;

    public UUID getSettingsUUID() {
        return settingsUUID;
    }

    public void setSettingsUUID(UUID settingsUUID) {
        this.settingsUUID = settingsUUID;
    }

    public boolean isNSFW() {
        return NSFW;
    }

    public void setNSFW(boolean NSFW) {
        this.NSFW = NSFW;
    }
}
