package com.floriantoenjes.lonely.user.settings;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserSettingsNotFoundException extends RuntimeException {

    public UserSettingsNotFoundException(String message) {
        super(message);
    }
}
