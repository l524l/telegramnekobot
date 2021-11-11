package com.github.l524l.telegramnekobot.nekosapi.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.exceptions.CategoryNotFound;
import com.github.l524l.telegramnekobot.nekosapi.NekoCategory;
import com.github.l524l.telegramnekobot.nekosapi.NekosApi;

import java.net.URL;

public class NekosLifeApi extends NekosApi {

    private final String API_URL = "https://nekos.life/api/v2/img/";

    @Override
    public URL execute(NekoCategory category) throws Exception {
        if (!nekoCategoryList.contains(category)) throw new CategoryNotFound(category);

        URL url = new URL(API_URL + category.getName());

        ObjectMapper objectMapper = new ObjectMapper();

        NekosLifeApiResponse response = objectMapper.readValue(url, NekosLifeApiResponse.class);

        return response.getUrl();
    }
}
