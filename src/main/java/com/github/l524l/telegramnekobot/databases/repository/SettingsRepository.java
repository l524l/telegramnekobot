package com.github.l524l.telegramnekobot.databases.repository;

import com.github.l524l.telegramnekobot.databases.entity.VariableSettingsOption;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

@Deprecated
public interface SettingsRepository extends KeyValueRepository<VariableSettingsOption, String> {
}