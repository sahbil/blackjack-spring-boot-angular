package com.vxcompany.blackjack;

import com.vxcompany.blackjack.domain.*;
import com.vxcompany.blackjack.exceptions.NotAbleToJoinException;
import com.vxcompany.blackjack.repository.DeckRepository;
import com.vxcompany.blackjack.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by xiabili on 13/06/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class DeckTest {
    private static Logger log = LoggerFactory.getLogger(DeckTest.class);
    private Deck deck;
    private Player player;
    private Player player2;
    private Player dealer;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Before
    public void setUp() {
        deck = new Deck();
        dealer = new Dealer();
        player = new Player("player1");
        player2 = new Player("player2");
        deck.addPlayer(dealer);
        deck.addPlayer(player);
        deck.addPlayer(player2);
        deck.addPlayer(new Player("player3"));
        testEntityManager.persist(deck);
        testEntityManager.persist(player2);
    }

    @Test
    public void newGameMustHaveADealer() {
        Deck deckTest = deckRepository.getOne(deck.getId());
        assertEquals(deckTest.getPlayerList().contains(dealer), true);
    }

    @Test
    public void dealerJoinedToDeck() {
        Dealer testDealer = playerRepository.getOneDealer(dealer.getId());
        Deck testDeck = deckRepository.findOne(deck.getId());
        assertEquals(testDealer.getDeck().getId(), testDeck.getId());
    }

    @Test(expected = NotAbleToJoinException.class)
    public void max3PlayerPerDeck() throws NotAbleToJoinException {
        Deck testDeck = deckRepository.findOne(deck.getId());
        addPlayer(testDeck, new Player("player4"));
    }

    private void addPlayer(Deck deck, Player player) throws NotAbleToJoinException {
        if (deck.getPlayerList().size() > 3) {
            throw new NotAbleToJoinException();
        }
        deck.addPlayer(player);
    }

    @Test
    public void shuffleCards() {
        Deck testDeck = deckRepository.findOne(deck.getId());
        testDeck.getCards().forEach(s -> {
            log.info(s.toString());
        });
        log.info("=======================");
        testDeck.shuffle();
        final Iterator<Card> iterator = testDeck.getCards().iterator();
        while (iterator.hasNext()) {
            log.info(iterator.next().toString());
        }
    }

    @Test
    public void testGame() {
        Deck testDeck = deckRepository.getOne(deck.getId());
        Dealer testDealer = playerRepository.getOneDealer(dealer.getId());
        Player testPlayer = playerRepository.getOne(player.getId());
        Player testPlayer2 = playerRepository.findOne(player2.getId());

        Set<Card> cards = testDeck.getCards();

        TestCard tCardAce = new TestCard(CardSuit.CLUBS, CardValue.ACE);
        TestCard tCard1 = new TestCard(CardSuit.CLUBS, CardValue.EIGHT);
        TestCard tCard2 = new TestCard(CardSuit.HEARTS, CardValue.TWO);
        TestCard tCard4 = new TestCard(CardSuit.SPADES, CardValue.TEN);

        Card card1 = cards.stream()
                .filter(x -> x.getValue() == tCard1.cardValue)
                .findFirst()
                .get(); // 8

        Card cardACE = cards.stream()
                .filter(x -> x.getValue() == tCardAce.cardValue)
                .findFirst()
                .get(); // 1

        Card card2 = cards.stream()
                .filter(x -> x.getValue() == tCard2.cardValue)
                .findFirst()
                .get();

        Card card4 = cards.stream()
                .filter(x -> x.getValue() == tCard4.cardValue)
                .findFirst()
                .get();


        // Dealer
        Hand dealerHand = testDealer.getHands().iterator().next();
        dealerHand.addCard(cardACE);
        dealerHand.addCard(card4);

        // Player 1
        Hand playerHand = testPlayer.getHands().iterator().next();
        playerHand.addCard(cardACE);
        playerHand.addCard(card2);

        assertEquals(true, dealerHand.isBlackJack());
        assertEquals(false, playerHand.isBlackJack());


        // Player 2
        Hand playerHand2 = testPlayer2.getHands().iterator().next();
        playerHand2.addCard(card2);
        playerHand2.addCard(card4);

        // player1: 14, player2: 12
        // winner dealer
        assertEquals(testDealer.getName(), whoIsWin(deck).getName());

//        Set<Player> blackJackWinners = whoIsWin(deck);
//        blackJackWinners.stream().forEach(t -> log.info("black jack: " + t.getClass()));

        // Contains returns always false.
//        assertEquals(false, blackJackWinners.contains(dealerHand));
//        assertEquals(true, blackJackWinners.contains(playerHand));

//        if (!beenSplit(testPlayer) && playerHand.canSplit()) {
//            testPlayer.addHand(makeSplit(playerHand));
//        }

//        playerHand.addCard(card3); //2
//        testPlayer.getHands().get(1).addCard(card4);

//        assertEquals(true, insertBet(testPlayer, testPlayer.getHands().get(1), 100));


        //dealer: 19
        // player: hand1: 4, hand2: 5


//        // Card moet weg van de deck
//        assertEquals(deck.getCards().contains(firstDealerCard), false);
//
//        // get player hand for playing
//        // player could have two hands
//        HashMap<Long, Card> playerCards = new HashMap<>();
//        Set<Hand> testPlayerHands = testPlayer.getHands();
//        for(Hand hand : testPlayerHands) {
//            Card c = dealCard(testDeck, hand);
//            playerCards.put(hand.getId(), c);
//            assertEquals(c.toString(), hand.getCards().iterator().next().toString());
//        }
//
//        // Player bets
//        for(Hand hand : testPlayerHands) {
//            hand.setBetAmount(100);
//        }
//
//        // SECOND CARD
//        Card secondDealerCard = dealCard(testDeck, dealerHand);
//
//
//
//        for(Hand hand : testPlayerHands) {
//            Card c = dealCard(testDeck, hand);
//            playerCards.put(hand.getId(), c);
//
//        }
    }

    private Card dealCard(Deck deck, Hand hand) {
        Card card = deck.getCards().iterator().next();
        if (card != null) {
            hand.addCard(card);
            deck.removeCard(card);
        }
        return card;
    }


    private class TestCard {
        public CardSuit suit;
        public CardValue cardValue;

        public TestCard(CardSuit s, CardValue v) {
            this.suit = s;
            this.cardValue = v;
        }
    }

    private Hand makeSplit(Hand hand) {
        Card splitCard = hand.getCards().remove(1);
        return new Hand(splitCard, hand.getBetAmount());
    }

    private boolean beenSplit(Player player) {
        return player.getHands().size() == 2;
    }

    private boolean insertBet(Player player, Hand hand, int betAmount) {
        if ((player.totalBetAmounts() + betAmount) > player.getCash())
            return false;
        hand.setBetAmount(betAmount);
        return true;
    }

    private boolean isBlackJack(Hand hand) {
        return (hand.getCards().size() == 2) && (hand.getCards().get(0).isAce() && hand.getCards().get(1).getCardValue() == 10);
    }

    private boolean isBusts(Hand hand) {
        return hand.getScore() > 21;
    }

    private boolean isPlayerWin(Hand hand, Hand dealerHand) {
        return (!isBusts(hand) && isBlackJack(hand)) &&
                !(!isBusts(dealerHand) && isBlackJack(dealerHand)) &&
                hand.getScore() > dealerHand.getScore();

    }

    private boolean isDealerWin(Hand hand, Hand playerHand) {
        return !isPlayerWin(playerHand, hand);
    }

    private Player whoIsWin(Deck deck) {

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

    private Player whoHasHighScore(Deck deck) {
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
}