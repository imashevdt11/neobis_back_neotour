package com.project.neobis_back_neotour.exceptions;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message, Integer status) {
        super(message, status);
    }
}