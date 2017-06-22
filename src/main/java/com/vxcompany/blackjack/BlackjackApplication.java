package com.vxcompany.blackjack;

import com.vxcompany.blackjack.repository.DeckRepository;
import com.vxcompany.blackjack.service.VXGameService;
import com.vxcompany.blackjack.service.VXGameServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackApplication implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(BlackjackApplication.class);
    @Autowired
    VXGameService service;

    public static void main(String[] args) {
        SpringApplication.run(BlackjackApplication.class, args);
        logger.debug("--Application Started--");
    }


    @Override
    public void run(String... strings) throws Exception {
        service.newDeck();
    }
}
