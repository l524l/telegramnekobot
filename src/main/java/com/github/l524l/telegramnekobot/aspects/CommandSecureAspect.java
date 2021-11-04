package com.github.l524l.telegramnekobot.aspects;

import com.github.l524l.telegramnekobot.annotations.SecureCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.user.BotUser;
import com.github.l524l.telegramnekobot.user.UserRole;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

@Aspect
@Component
public class CommandSecureAspect {

    private final TelegramSender dataSender;

    @Autowired
    public CommandSecureAspect(TelegramSender dataSender) {
        this.dataSender = dataSender;
    }

    @Pointcut("execution(* com.github.l524l.telegramnekobot.commands.Command.execute(..)) && @within(com.github.l524l.telegramnekobot.annotations.SecureCommand)")
    public void secureCommand(){

    }

    @Around("secureCommand()")
    public void secure(ProceedingJoinPoint joinPoint) throws Throwable {
        Command command = (Command) joinPoint.getTarget();
        ClassPathResource resource = new ClassPathResource("/img/403.png");

        UserRole[] requiredAuthorities = command.getClass()
                                                .getAnnotation(SecureCommand.class)
                                                .authorities();

        BotUser user = command.getContext().getBotUser();
        Message message = command.getContext().getUpdate().getMessage();

        for (UserRole role :
                requiredAuthorities) {

            if (user.hasRole(role)) {
                joinPoint.proceed();
                return;
            }
        }

        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(message.getChatId().toString())
                .photo(new InputFile(resource.getInputStream(), "403"))
                .build();
        dataSender.execute(sendPhoto);
    }
}
