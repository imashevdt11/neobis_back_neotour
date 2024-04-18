package com.project.neobis_back_neotour.exceptions;

public class ReviewNotFoundException extends BaseException {
    public ReviewNotFoundException(String message, Integer status) {
        super(message, status);
    }
}