package com.vxcompany.blackjack.controller;

import com.vxcompany.blackjack.domain.Player;
import com.vxcompany.blackjack.service.VXGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiabili on 21/06/2017.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("player")
public class PlayerController {
    private static final Logger logger = LoggerFactory.getLogger(BasicBlackJackController.class);

    @Autowired
    VXGameService vxGameService;

    @RequestMapping("/{id}")
    public Player dealer(@PathVariable String id) {
        return vxGameService.getPlayer(id);
    }
}
