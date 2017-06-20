package com.vxcompany.blackjack.repository;

import com.vxcompany.blackjack.domain.Hand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiabili on 12/06/2017.
 */
@Transactional
public interface HandRepository extends JpaRepository<Hand, Long> {


}
