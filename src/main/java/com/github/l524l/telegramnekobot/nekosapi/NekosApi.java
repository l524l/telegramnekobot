package com.github.l524l.telegramnekobot.nekosapi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NekosApi {
    private final List<NekoCategory> categories;
    private static final String NEKO_URL = "http://api.nekos.fun:8080/api/";

    public NekosApi(List<NekoCategory> categories) {
        this.categories = categories;
    }

    public String execute(String category){
        AtomicReference<NekoCategory> nekoCategory = new AtomicReference<>();
        categories.forEach((x) -> {
            if (x.getName().equals(category)) nekoCategory.set(x);
        });
        NekoApiResponse response = null;
        try {
            URL url = new URL(NEKO_URL + nekoCategory.get().getName());
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(url, NekoApiResponse.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.getImage();
    }

    public List<NekoCategory> getCategories() {
        return categories;
    }

    public static String getURL() {
        return NEKO_URL;
    }
}
