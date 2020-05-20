package com.qstpss.kafkaserver.services;

import com.qstpss.kafkaserver.StarshipDto;

public interface StarshipService {
    void send(StarshipDto dto);
    void consume(StarshipDto dto);
}
