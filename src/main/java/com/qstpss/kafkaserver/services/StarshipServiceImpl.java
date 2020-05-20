package com.qstpss.kafkaserver.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qstpss.kafkaserver.AbstractDto;
import com.qstpss.kafkaserver.StarshipDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StarshipServiceImpl  implements StarshipService{
    private final KafkaTemplate<Long, AbstractDto> kafkaAbstractTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StarshipServiceImpl(KafkaTemplate<Long, AbstractDto> kafkaAbstractTemplate, ObjectMapper objectMapper) {
        this.kafkaAbstractTemplate = kafkaAbstractTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void send(StarshipDto dto) {
        kafkaAbstractTemplate.send("server.starship", dto);
    }

    @KafkaListener(id = "someId", topics = {"server.starship"}, containerFactory = "singleFactory")
    @Override
    public void consume(StarshipDto dto) {
        System.out.println("=> consumed:" + writeValueAsString(dto));
    }

    private String writeValueAsString(StarshipDto dto) {
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
