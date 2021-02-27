package com.github.l524l.telegramnekobot.telegram;

import com.github.l524l.telegramnekobot.exceptions.BotException;
import com.github.l524l.telegramnekobot.settings.ProgramSettings;
import com.github.l524l.telegramnekobot.telegram.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CommandsExecutionProtector {
    private final Logger logger = LoggerFactory.getLogger(CommandsExecutionProtector.class);
    @Autowired
    private ProgramSettings settings;

    public void execute(Command command, Update update) throws BotException {
        CommandExecutor executor = createExecutor(update);
        if (command.getRequired_role().isLessPriority(executor.getRole())) command.execute(update, executor);
    }
    private CommandExecutor createExecutor(Update update){
        String username = update.getMessage().getFrom().getUserName();
        UserRoles role;
        List<String> adminList = settings.getAdminList();
        if (settings.getOwner().equals(username)) {
            role = UserRoles.OWNER;
        } else if (adminList.contains(username)){
            role = UserRoles.ADMIN;
        } else role = UserRoles.USER;
        return new CommandExecutor(username, role);
    }
}
