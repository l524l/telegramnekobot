package com.github.l524l.telegramnekobot.databases.entity;

import com.github.l524l.telegramnekobot.settings.WorkMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("settings")
@Deprecated
public class VariableSettingsOption {
    @Id
    private String id = "program_settings";
    private WorkMode workMode;
    private List<String> adminList;

    public VariableSettingsOption(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
