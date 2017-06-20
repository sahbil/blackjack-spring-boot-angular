package com.vxcompany.blackjack.strategy;

import com.vxcompany.blackjack.domain.Card;
import com.vxcompany.blackjack.domain.Hand;

/**
 * Created by xiabili on 19/06/2017.
 */
public interface PlayerStrategy {
    static enum DealerStrategyAction {
        HIT,
        STAND,
        SPLIT
    }

    PlayerStrategy getAction(Hand hand);

    void joinDeck();
}
