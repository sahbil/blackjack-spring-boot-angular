package com.vxcompany.blackjack.service;

import com.vxcompany.blackjack.domain.*;
import com.vxcompany.blackjack.exceptions.NotAbleToJoinException;

import java.util.List;

/**
 * Created by xiabili on 13/06/2017.
 */
public interface VXGameService {
    List<Deck> getDecks();

    Deck newDeck();

    boolean addPlayer(Deck deck, Player player) throws NotAbleToJoinException;

    Deck shuffleCards(Deck deck);

    Hand dealerHand(Dealer dealer);

    List<Hand> playerHands(Player player);

    Card dealCard(Deck deck, Hand hand);

    Hand splitHand(Player player, Hand hand);

    boolean isPlayerWin(Hand hand, Hand dealerHand);

    boolean isDealerWin(Hand hand, Hand playerHand);

    Player whoIsWin(Deck deck);

    Player whoHasHighScore(Deck deck);

    void saveDeck(Deck deck);
}
