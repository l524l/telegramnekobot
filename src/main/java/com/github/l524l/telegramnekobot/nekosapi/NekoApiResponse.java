package com.github.l524l.telegramnekobot.nekosapi;

import java.net.URL;

public class NekoApiResponse {
    private URL image;
    private String error;

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
