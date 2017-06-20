package com.vxcompany.blackjack.domain;

/**
 * Card Suit is using for Card object
 * <p>
 * Created by xiabili on 08/06/2017.
 */
public enum CardSuit {
    SPADES("Spades"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts");

    private final String name;

    CardSuit(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
