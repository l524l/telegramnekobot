package com.github.l524l.telegramnekobot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotPropertyRepository extends CrudRepository<String,String> {
}
