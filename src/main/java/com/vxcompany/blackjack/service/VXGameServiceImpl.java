package com.vxcompany.blackjack.service;

import com.vxcompany.blackjack.config.CONSTANT;
import com.vxcompany.blackjack.domain.*;
import com.vxcompany.blackjack.exceptions.NotAbleToJoinException;
import com.vxcompany.blackjack.repository.DeckRepository;
import com.vxcompany.blackjack.repository.HandRepository;
import com.vxcompany.blackjack.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xiabili on 13/06/2017.
 */

public class VXGameServiceImpl implements VXGameService {

    @Autowired
    DeckRepository deckRepository;
    @Autowired
    HandRepository handRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Deck> getDecks() {
        return deckRepository.findAll();
    }

    @Override
    public Deck newDeck() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        deck.addPlayer(dealer);
        saveDeck(deck);
        return deck;
    }

    @Override
    public boolean addPlayer(Deck deck, Player player) throws NotAbleToJoinException {
        if (deck.getPlayerList().size() > CONSTANT.MAX_PLAYER_PER_DECK) {
            throw new NotAbleToJoinException();
        }
        deck.addPlayer(player);
        saveDeck(deck);
        playerRepository.save(player);
        return true;
    }

    @Override
    public Deck shuffleCards(Deck deck) {
        deck.shuffle();
        saveDeck(deck);
        return deck;
    }

    @Override
    public Hand dealerHand(Dealer dealer) {
        return dealer.getHands().get(0);
    }

    @Override
    public List<Hand> playerHands(Player player) {
        return player.getHands();
    }

    @Override
    public Card dealCard(Deck deck, Hand hand) {
        Card card = deck.getCards().iterator().next();
        if (card != null) {
            hand.addCard(card);
            deck.removeCard(card);
            saveDeck(deck);
            handRepository.save(hand);
        }
        return card;
    }

    @Override
    public Hand splitHand(Player player, Hand handLeft) {
        if (player.beenSplit())
            return null;
        Card splitCard = handLeft.getCards().remove(1);
        Hand handRight = new Hand(splitCard, handLeft.getBetAmount());
        handRepository.save(handLeft);
        handRepository.save(handRight);
        return handRight;
    }

    @Override
    public boolean isPlayerWin(Hand hand, Hand dealerHand) {
        return (!hand.isBusts() && hand.isBlackJack()) &&
                !(!dealerHand.isBusts() && dealerHand.isBlackJack()) &&
                hand.getScore() > dealerHand.getScore();

    }

    @Override
    public boolean isDealerWin(Hand hand, Hand playerHand) {
        return !isPlayerWin(playerHand, hand);
    }

    @Override
    public Player whoIsWin(Deck deck) {
        Set<Player> players = deck.getPlayerList();
        return players
                .stream()
                .flatMap(s -> s.getHands().stream())
                .filter(h -> !h.isBusts())
                .filter(h -> h.isBlackJack())
                .collect(Collectors.toSet())
                .stream()
                .map(Hand::getPlayer)
                .findFirst()
                .orElse(whoHasHighScore(deck));
    }

    @Override
    public Player whoHasHighScore(Deck deck) {
        Set<Player> players = deck.getPlayerList();
        return players
                .stream()
                .flatMap(s -> s.getHands().stream())
                .filter(h -> !h.isBusts())
                .filter(h -> h.isBlackJack())
                .max(Comparator.comparingInt(Hand::getScore))
                .map(hand -> hand.getPlayer())
                .get();
    }

    @Override
    public void saveDeck(Deck deck) {
        deckRepository.save(deck);
    }
}
