package com.vxcompany.blackjack.strategy;

import com.vxcompany.blackjack.domain.Hand;

/**
 * Created by xiabili on 19/06/2017.
 */
public interface DealerStrategy {
    public static enum DealerStrategyAction {
        HIT,
        STAND
    }

    public DealerStrategy getAction(Hand hand);
}
