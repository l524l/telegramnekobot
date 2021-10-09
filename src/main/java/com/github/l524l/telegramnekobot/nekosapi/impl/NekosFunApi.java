package com.github.l524l.telegramnekobot.nekosapi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.exceptions.CategoryNotFound;
import com.github.l524l.telegramnekobot.nekosapi.NekoCategory;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;

import java.io.IOException;
import java.net.URL;

public class NekosFunApi extends NekosApi {

    private final String NEKO_URL = "http://api.nekos.fun:8080/api/";

    @Override
    public URL execute(NekoCategory category) throws IOException {
        if (!nekoCategoryList.contains(category)) throw new CategoryNotFound(category);

        URL url = new URL(NEKO_URL + category.getName());

        ObjectMapper objectMapper = new ObjectMapper();

        NekosFunApiResponse response = objectMapper.readValue(url, NekosFunApiResponse.class);

        return response.getImage();
    }
}
