package com.github.l524l.telegramnekobot.commands.inline;

import com.github.l524l.telegramnekobot.commands.Command;

import java.util.Collections;
import java.util.Set;

public abstract class InlineCommand extends Command {

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @Override
    public Set<String> getAliases() {
        return Collections.emptySet();
    }
}
