package com.vxcompany.blackjack.repository;

import com.vxcompany.blackjack.domain.Dealer;
import com.vxcompany.blackjack.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiabili on 12/06/2017.
 */
@Transactional
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE id = :id AND is_dealer = 1")
    Dealer getOneDealer(@Param("id") long id);

}
