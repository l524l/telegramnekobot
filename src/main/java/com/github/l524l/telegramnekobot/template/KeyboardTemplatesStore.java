package com.github.l524l.telegramnekobot.template;

import java.util.HashMap;
import java.util.Map;

public class KeyboardTemplatesStore {
    private Map<String, KeyboardTemplate> templates;

    public KeyboardTemplatesStore() {
        templates = new HashMap<>();
    }

    public KeyboardTemplate getTemplate(String key) {
        return templates.get(key);
    }

    public void addTemplate(String key, KeyboardTemplate value){
        templates.put(key, value);
    }

    public void removeTemplate(String key) {
        templates.remove(key);
    }

    public Map<String, KeyboardTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, KeyboardTemplate> templates)
    {
        this.templates = templates;
    }
}
