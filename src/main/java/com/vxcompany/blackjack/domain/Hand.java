package com.vxcompany.blackjack.domain;

import javax.persistence.*;
import java.util.*;

/**
 * Created by xiabili on 19/06/2017.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "hand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new LinkedList<>();

    private int betAmount;
    private boolean finished = false;

    public Hand() {

    }

    public Hand(Card card, int betAmount) {
        getCards().add(card);
        this.betAmount = betAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.getCards().add(card);
        card.setHand(this);
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getScore() {
        int score = cards.stream()
                .filter(t -> t.getValue() != CardValue.ACE)
                .mapToInt(Card::getCardValue)
                .sum();
        int aceScore = cards.stream()
                .filter(t -> t.getValue() == CardValue.ACE)
                .mapToInt(i -> score <= 10 ? 11 : 1)
                .sum();
        return score + aceScore;
    }

    public boolean canSplit() {
        return (cards.size() == 2) && (cards.get(0).getValue() == cards.get(1).getValue());
    }

    public boolean isBlackJack() {
        return (this.getCards().size() == 2) && (this.getCards().get(0).isAce() && this.getCards().get(1).getCardValue() == 10);
    }

    public boolean isBusts() {
        return this.getScore() > 21;
    }
}
