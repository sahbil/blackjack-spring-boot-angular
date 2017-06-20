package com.vxcompany.blackjack.controller;

import com.vxcompany.blackjack.domain.Dealer;
import com.vxcompany.blackjack.domain.Deck;
import com.vxcompany.blackjack.domain.Player;
import com.vxcompany.blackjack.repository.DeckRepository;
import com.vxcompany.blackjack.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xiabili on 12/06/2017.
 */
@Controller
public class BlackJackController {

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    PlayerRepository playerRepository;
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String developersList(Model model) {
//        model.addAttribute("decks", deckRepository.findAll());
//        return "decks";
//    }
//
//    @RequestMapping(value = "/deck/{id}")
//    public String deck(@PathVariable Long id, Model model) {
//        model.addAttribute("deck", deckRepository.findById(id));
//        model.addAttribute("players", playerRepository.listAllPerDeck(id));
//        return "deck";
//    }
//
//    @RequestMapping(value = "/deck/{deckId}/{playerId}", method = RequestMethod.POST)
//    public String join(@PathVariable Long deckId, @PathVariable Long playerId, Model model) {
//        Deck deck = deckRepository.findById(deckId).get();
//        Player player = playerRepository.findById(playerId).get();
//
//        if (deck.isAbleToJoin()) {
//            deck.addUser(player);
//            player.setDeckId(deckId);
//            deckRepository.save(deck);
//            playerRepository.save(player);
//        }
//        model.addAttribute("deck", deckRepository.findById(deckId));
//        model.addAttribute("players", playerRepository.listAllPerDeck(deckId));
//
//        return "redirect:/deck/" + deckId;
//    }
//
//    @RequestMapping(value = "/deck/new", method = RequestMethod.POST)
//    public String createDeck(Model model) {
//        Deck deck = new Deck();
//        Player dealer = new Dealer();
//        dealer.setDeckId(deck.getId());
//        deck.addUser(dealer);
//        deckRepository.save(deck);
//        playerRepository.save(dealer);
//        model.addAttribute("decks", deckRepository.findAll());
//        return "redirect:/decks";
//    }
//
//    @RequestMapping(value = "/player/new", method = RequestMethod.POST)
//    public String createPlayer(@PathVariable String name, Model model) {
//        Player dealer = new Player(name);
//        playerRepository.save(dealer);
//        model.addAttribute("decks", deckRepository.findAll());
//        return "redirect:/decks";
//    }

}
