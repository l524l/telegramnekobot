package com.github.l524l.telegramnekobot.nekosapi;

import com.google.gson.annotations.SerializedName;

public class NekoCategory {

    @SerializedName("name")
    private String name;
    @SerializedName("nsfw")
    private Boolean nsfw;

    public String getName() {
        return name;
    }

    public Boolean isNsfw() {
        return nsfw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NekoCategory that = (NekoCategory) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return nsfw != null ? nsfw.equals(that.nsfw) : that.nsfw == null;
    }

}
