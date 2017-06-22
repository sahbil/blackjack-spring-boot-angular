package com.vxcompany.blackjack.controller;

import com.vxcompany.blackjack.domain.Card;
import com.vxcompany.blackjack.domain.Deck;
import com.vxcompany.blackjack.domain.Hand;
import com.vxcompany.blackjack.domain.Player;
import com.vxcompany.blackjack.exceptions.NotAbleToJoinException;
import com.vxcompany.blackjack.service.VXGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiabili on 21/06/2017.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    VXGameService vxGameService;

    @RequestMapping(value = "/{id}")
    public Deck newGame(@PathVariable long id) {
        return vxGameService.getDeck(id);
    }

    @RequestMapping(value = "/{id}/join-game", method = RequestMethod.POST)
    public Deck joinGame(@PathVariable long id, @RequestBody Player player) throws NotAbleToJoinException {
        vxGameService.savePlayer(player);
        Deck deck = vxGameService.getDeck(id);
        vxGameService.addPlayer(deck, player);
        return deck;
    }

    @RequestMapping(value = "/{id}/deal-card/{handId}", method = RequestMethod.POST)
    public Card dealCard(@PathVariable long id, @PathVariable long handId) {
        Deck deck = vxGameService.getDeck(id);
        Hand hand = vxGameService.getHand(handId);
        return vxGameService.dealCard(deck, hand);
    }
}
