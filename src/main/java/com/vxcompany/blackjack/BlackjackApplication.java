package com.vxcompany.blackjack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackjackApplication {
    private static final Logger logger = LoggerFactory.getLogger(BlackjackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BlackjackApplication.class, args);
        logger.debug("--Application Started--");
    }
}
