package com.vxcompany.blackjack.domain;

import javax.persistence.*;

import java.util.*;

/**
 * Created by xiabili on 12/06/2017.
 */
@Entity
@Table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deck_id")
    private Deck deck;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hand> hands = new ArrayList<>();

    private int cash = 200;
    private boolean isDealer = false;

    public Player(String name) {
        setName(name);
        addHand(new Hand());
    }

    public boolean isDealer() {
        return isDealer;
    }

    public void setDealer(boolean dealer) {
        isDealer = dealer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public void addHand(Hand hand){
        this.getHands().add(hand);
        hand.setPlayer(this);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int totalBetAmounts() {
        return getHands()
                .stream()
                .mapToInt(Hand::getBetAmount)
                .sum();
    }

    public boolean beenSplit() {
        return getHands().size() == 2;
    }

    @Override
    public boolean equals(Object o) {
        // TESTING
        return true;
//        if (this == o) return true;
//        if (o == null || getClass().equals(o.getClass())) return false;
//
//        Player player = (Player) o;
//
//        if (id != null ? !id.equals(player.id) : player.id != null) return false;
//        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + name.hashCode();
        // TESTING
        return getName().length();
    }
}
