package com.github.l524l.telegramnekobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.generics.TelegramBot;

import javax.naming.InitialContext;

@SpringBootApplication
public class TelegramnekobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramnekobotApplication.class, args);
	}

}
