package com.vxcompany.blackjack.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vxcompany.blackjack.domain.Card;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiabili on 12/06/2017.
 */
public class CardConverter implements AttributeConverter<List<Card>, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private String convertToDatabaseColumn(Card card) {
        try {
            return objectMapper.writeValueAsString(card);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertToDatabaseColumn(List<Card> cards) {
        try {
            return objectMapper.writeValueAsString(cards);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Card> convertToEntityAttribute(String s) {
//        try {
//            return objectMapper.readValue(s, List<Card>);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
