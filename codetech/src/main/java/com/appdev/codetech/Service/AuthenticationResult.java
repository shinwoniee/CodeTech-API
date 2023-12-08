package com.appdev.codetech.Service;

import com.appdev.codetech.Entity.UserEntity;

public class AuthenticationResult {
    private final boolean success;
    private final boolean invalidPassword;
    private final boolean usernameOrEmailNotFound;
    private final UserEntity user;

    public AuthenticationResult(boolean success, boolean invalidPassword, boolean usernameOrEmailNotFound,
            UserEntity user) {
        this.success = success;
        this.invalidPassword = invalidPassword;
        this.usernameOrEmailNotFound = usernameOrEmailNotFound;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isInvalidPassword() {
        return invalidPassword;
    }

    public boolean isUsernameOrEmailNotFound() {
        return usernameOrEmailNotFound;
    }

    public UserEntity getUser() {
        return user;
    }
}