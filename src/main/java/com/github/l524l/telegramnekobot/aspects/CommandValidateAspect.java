package com.github.l524l.telegramnekobot.aspects;

import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.exceptions.ValidationException;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Aspect
@Component
public class CommandValidateAspect {

    private final TelegramSender dataSender;

    @Autowired
    public CommandValidateAspect(TelegramSender dataSender) {
        this.dataSender = dataSender;
    }

    @Pointcut("execution(* com.github.l524l.telegramnekobot.commands.Command.execute(..))")
    public void validateCommand(){

    }

    @Around("validateCommand()")
    public void validCommand(ProceedingJoinPoint joinPoint) throws TelegramApiException {
        Command command = (Command) joinPoint.getTarget();

        try {
            command.validate();
            joinPoint.proceed();
        } catch (ValidationException e) {
            SendMessage sendMessage = SendMessage.
                    builder()
                    .chatId(command.getMessage().getChatId().toString())
                    .text(e.getMessage())
                    .build();
            dataSender.execute(sendMessage);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
