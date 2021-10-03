package com.github.l524l.telegramnekobot.repositories;

import com.github.l524l.telegramnekobot.user.BotUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BotUser, Integer> {
    Optional<BotUser> findBotUserByUsername(String name);
}
