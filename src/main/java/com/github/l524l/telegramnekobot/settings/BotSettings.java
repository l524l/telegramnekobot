package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BotSettings {

    private String botToken;
    private String botUsername;
    private String botPath;
    private WorkMode workMode;
    private String ownerID;
    private List<String> adminList;

    private final Logger logger = LoggerFactory.getLogger(BotSettings.class);

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotPath() {
        return botPath;
    }

    public void setBotPath(String botPath) {
        this.botPath = botPath;
    }

    public String getOwner() {
        return ownerID;
    }

    public void setOwner(String owner) {
        ownerID = owner;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkMode workMode) {
        this.workMode = workMode;
    }

    public List<String> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<String> adminList) {
        this.adminList = adminList;
    }

    public static ProgramSettingsBuilder builder(){
        return new ProgramSettingsBuilder();
    }

    public boolean addAdmin(String userId){
        if (!adminList.contains(userId)){
            adminList.add(userId);
            return true;
        } else return false;
    }
    public boolean removeAdmin(String adminId) {
        return adminList.remove(adminId);
    }

    public boolean saveSettings(){
        File file = new File("./settings.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            file.createNewFile();
            objectMapper.writeValue(file, this);
            logger.info("Saved settings file");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static class ProgramSettingsBuilder {

        private WorkMode workMode;
        private String ownerID;
        private List<String> adminList;
        private String botToken;
        private String botUsername;
        private String botPath;

        public ProgramSettingsBuilder botToken(String botToken) {
            this.botToken = botToken;
            return this;
        }

        public ProgramSettingsBuilder botUsername(String botUsername) {
            this.botUsername = botUsername;
            return this;
        }

        public ProgramSettingsBuilder botPath(String botPath) {
            this.botPath = botPath;
            return this;
        }

        public ProgramSettingsBuilder workMode(WorkMode workMode) {
            this.workMode = workMode;
            return this;
        }

        public ProgramSettingsBuilder ownerID(String ownerID) {
            this.ownerID = ownerID;
            return this;
        }

        public ProgramSettingsBuilder adminList(List<String> adminList) {
            this.adminList = adminList;
            return this;
        }

        public BotSettings build(){
            BotSettings settings = new BotSettings();
            settings.setOwner(ownerID);
            settings.setAdminList(adminList);
            settings.setWorkMode(workMode);
            settings.setBotPath(botPath);
            settings.setBotToken(botToken);
            settings.setBotUsername(botUsername);
            return settings;
        }
    }
}
