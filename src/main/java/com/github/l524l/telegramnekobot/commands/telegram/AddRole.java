package com.github.l524l.telegramnekobot.commands.telegram;

import com.github.l524l.telegramnekobot.annotations.BotCommand;
import com.github.l524l.telegramnekobot.annotations.SecureCommand;
import com.github.l524l.telegramnekobot.commands.Command;
import com.github.l524l.telegramnekobot.exceptions.ValidationException;
import com.github.l524l.telegramnekobot.repositories.UserRepository;
import com.github.l524l.telegramnekobot.telegram.TelegramSender;
import com.github.l524l.telegramnekobot.user.BotUser;
import com.github.l524l.telegramnekobot.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;

@BotCommand("ADD_ROLE_COMMAND")
@SecureCommand(authorities = {UserRole.OWNER})
public class AddRole extends Command {

    private final TelegramSender telegramSender;
    private final UserRepository userRepository;

    @Autowired
    public AddRole(TelegramSender telegramSender, UserRepository userRepository) {
        this.telegramSender = telegramSender;
        this.userRepository = userRepository;
    }

    @Override
    public void execute() throws Exception {
            SendMessage.SendMessageBuilder sendMessageBuilder = SendMessage.builder()
                    .chatId(message.getChatId().toString());

            String username = parameters.get(0);
            String role = parameters.get(1).toUpperCase();

            UserRole newRole = UserRole.valueOf(role);

            Optional<BotUser> optionalBotUser = userRepository.findBotUserByUsername(username);

            if (optionalBotUser.isPresent()) {
                BotUser botUser = optionalBotUser.get();
                botUser.addRole(newRole);
                userRepository.save(botUser);
                sendMessageBuilder.text("Роль успешно добавлена");
            } else {
                sendMessageBuilder.text(String.format("Пользователь %s не найден", username));
            }

            telegramSender.execute(sendMessageBuilder.build());
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();

        if (parameters.size() != 2)
            throw new ValidationException("Ожидались параметры: /addrole {username} {role}");

        if (UserRole.isExist(parameters.get(1).toUpperCase()))
            throw new ValidationException(String.format("Роль %s не существует", parameters.get(1).toUpperCase())) ;
    }
}
