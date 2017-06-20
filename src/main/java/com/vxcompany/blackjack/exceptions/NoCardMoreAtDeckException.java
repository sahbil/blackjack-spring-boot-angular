package com.vxcompany.blackjack.exceptions;

/**
 * Created by xiabili on 12/06/2017.
 */
public class NoCardMoreAtDeckException extends Exception {
    public NoCardMoreAtDeckException() {
        this("No card more!");
    }

    public NoCardMoreAtDeckException(String message) {
        super(message);
    }
}
