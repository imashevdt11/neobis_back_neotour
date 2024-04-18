package com.project.neobis_back_neotour.exceptions;

public class BookingNotFoundException extends BaseException {
    public BookingNotFoundException(String message, Integer status) {
        super(message, status);
    }
}