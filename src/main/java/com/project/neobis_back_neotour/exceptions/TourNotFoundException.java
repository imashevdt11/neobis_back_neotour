package com.project.neobis_back_neotour.exceptions;

public class TourNotFoundException extends BaseException {
    public TourNotFoundException(String message, Integer status) {
        super(message, status);
    }
}
