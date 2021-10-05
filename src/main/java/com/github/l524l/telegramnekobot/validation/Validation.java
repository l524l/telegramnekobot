package com.github.l524l.telegramnekobot.validation;

import com.github.l524l.telegramnekobot.exceptions.ValidationException;

public interface Validation {
    void validate() throws ValidationException;
}
