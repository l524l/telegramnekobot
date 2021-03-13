package com.github.l524l.telegramnekobot.settings;

import com.github.l524l.telegramnekobot.databases.entity.VariableSettingsOption;
import com.github.l524l.telegramnekobot.databases.repository.SettingsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;

import java.util.List;

public class BotSettings {

    private SettingsRepository settingsRepository;
    @Id
    private final String id = "program_settings";
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

    public SettingsRepository getSettingsRepository() {
        return settingsRepository;
    }

    public void setSettingsRepository(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
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
        VariableSettingsOption options = new VariableSettingsOption();
        options.setAdminList(adminList);
        options.setWorkMode(workMode);
        settingsRepository.save(options);
        logger.info("Saved settings file");
        return true;
    }

    public static class ProgramSettingsBuilder {

        private WorkMode workMode;
        private String ownerID;
        private List<String> adminList;
        private String botToken;
        private String botUsername;
        private String botPath;
        private SettingsRepository repository;

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

        public ProgramSettingsBuilder repository(SettingsRepository repository) {
            this.repository = repository;
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
            settings.setSettingsRepository(repository);

            return settings;
        }
    }
}
