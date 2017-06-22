package com.vxcompany.blackjack.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "suit")
    @Enumerated(EnumType.STRING)
    private CardSuit suit;

    @Column(name = "value")
    @Enumerated(EnumType.STRING)
    private CardValue value;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    @JsonBackReference
    private Deck deck;

    @ManyToOne
    @JoinColumn(name = "hand_id", nullable = true)
    @JsonBackReference
    private Hand hand;

    public Card(CardSuit suit, CardValue faceValue) {
        this.suit = suit;
        this.value = faceValue;
    }

    public Card(CardSuit suit, CardValue faceValue, Deck deck) {
        this(suit, faceValue);
        this.deck = deck;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public boolean isAce() {
        return getValue().getName().equalsIgnoreCase("ACE");
    }

    public int getCardValue() {
        return value.getValue();
    }

    public String toString() {
        return getSuit().toString() + "/" + getValue().getName() + "/" + getCardValue();
    }
}
