package com.github.l524l.telegramnekobot.nekosapi;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class NekoApiConfig {
    @Bean
    public NekosApi createNekoApi(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<NekoCategory> list = null;
        list = new ArrayList<>();
        list.add(new NekoCategory("AS",true));
        NekosApi nekosApi = null;
        try {
            String s = objectMapper.writeValueAsString(list);
            ClassPathResource urlResource = new ClassPathResource("./nekocategories.json");
            list = objectMapper.readValue(urlResource.getInputStream(), new TypeReference<List<NekoCategory>>(){});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new NekosApi(list);
    }
}

