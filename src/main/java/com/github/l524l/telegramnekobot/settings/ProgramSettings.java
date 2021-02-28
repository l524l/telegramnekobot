package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l524l.telegramnekobot.nekosapi.NekoApiConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProgramSettings {
    private WorkMode workMode;
    private String ownerID;
    private List<String> adminList;

    private final Logger logger = LoggerFactory.getLogger(ProgramSettings.class);

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

    public void addAdmin(String userId){
        if (adminList.contains(userId)){
            adminList.add(userId);
        }
    }
    public void removeAdmin(String adminId){
        adminList.removeIf((x) -> x.equals(adminId));
    }

    private boolean saveSettings(){
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

        public ProgramSettings build(){
            ProgramSettings settings = new ProgramSettings();
            settings.setOwner(ownerID);
            settings.setAdminList(adminList);
            settings.setWorkMode(workMode);
            return settings;
        }
    }
}
