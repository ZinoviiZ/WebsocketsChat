package com.risingapp.likeit.enums;

import lombok.AllArgsConstructor;

/**
 * Created by zinoviyzubko on 07.04.17.
 */
@AllArgsConstructor
public enum ErrorStatus {

    INTERNAL_ERROR(1, "Internal server error"),
    EMAIL_ALREADY_REGISTERED(10, "User with that email is already registered"),
    FORBIDDEN(11, "User have not enough rules"),
    NOT_AUTHORIZED(12, "User is not authorized"),
    AUTHORIZE_FAILURE(13, "Authorize failure"),
    SESSION_TIME_OUT(21, "Session is time out. Please, login again");

    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
