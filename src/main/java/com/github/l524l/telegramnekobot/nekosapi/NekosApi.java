package com.github.l524l.telegramnekobot.nekosapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class NekosApi {
    @Autowired
    private ProgramSettings programSettings;
    private final List<NekoCategory> categories;
    private static final String NEKO_URL = "http://api.nekos.fun:8080/api/";

    public NekosApi(List<NekoCategory> categories) {
        this.categories = categories;
    }

    public URL execute(String category) throws NekosApiException {
        AtomicReference<NekoCategory> nekoCategory = new AtomicReference<>();
        categories.forEach((x) -> {
            if (x.getName().equals(category)) nekoCategory.set(x);
        });
        if (nekoCategory.get() == null) throw new NekosApiException("Category doesn't exist", 101);
        if (nekoCategory.get().isNsfw() && !programSettings.getWorkMode().isNsfw()) throw new NekosApiException("NSFW mode is disabled", 103);
            try {
            URL url = new URL(NEKO_URL + nekoCategory.get().getName());
            ObjectMapper objectMapper = new ObjectMapper();
            NekoApiResponse response = objectMapper.readValue(url, NekoApiResponse.class);
            return response.getImage();
        } catch (IOException e) {
            throw new NekosApiException("Fail conected to api", 100 ,e);
        }
    }

    public List<NekoCategory> getCategories() {
        return categories;
    }

    public static String getURL() {
        return NEKO_URL;
    }
}
