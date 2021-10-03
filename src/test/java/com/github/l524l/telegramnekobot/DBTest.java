package com.github.l524l.telegramnekobot;

import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.user.BotUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DBTest {

    @Autowired
    private UserRepository repository;

    @Test
    void saveUser() {
        BotUser botUser = new BotUser(0,"112","12","as");

        repository.save(botUser);
    }
}
