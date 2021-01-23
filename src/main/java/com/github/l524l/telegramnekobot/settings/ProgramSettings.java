package com.github.l524l.telegramnekobot.settings;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProgramSettings {
    private WorkMode workMode;
    private String ownerID;
    private List<String> adminList;

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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
