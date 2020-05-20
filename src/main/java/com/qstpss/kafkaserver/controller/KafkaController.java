package com.qstpss.kafkaserver.controller;

import com.qstpss.kafkaserver.StarshipDto;
import com.qstpss.kafkaserver.conf.KafkaConsumerConfig;
import com.qstpss.kafkaserver.services.StarshipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    private StarshipServiceImpl starshipService;
    @GetMapping("/send")
    public void sendMsg() {
        starshipService.send(createStarship());
    }

    private StarshipDto createStarship() {
        StarshipDto dto = new StarshipDto();
        dto.setEngineCount(312);
        dto.setMaxSpeed(31000);
        dto.setName("VVSAD-41D1S");
        return dto;
    }
}
