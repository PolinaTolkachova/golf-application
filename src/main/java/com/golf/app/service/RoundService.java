package com.golf.app.service;

import com.golf.app.model.Round;

import java.util.Optional;

public interface RoundService {

    Iterable<Round> getAllRound();

    Iterable<Round> getAllRoundByCompetitionName(String name);

    Optional<Round> getRoundById(Long id);
    Round saveRound(Round round);
    void deleteRound(Long id);
}
