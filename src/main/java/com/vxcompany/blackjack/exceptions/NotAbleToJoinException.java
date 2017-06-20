package com.vxcompany.blackjack.exceptions;

/**
 * Created by xiabili on 12/06/2017.
 */
public class NotAbleToJoinException extends Exception {
    public NotAbleToJoinException() {
        this("The deck is full.");
    }

    public NotAbleToJoinException(String message) {
        super(message);
    }
}
