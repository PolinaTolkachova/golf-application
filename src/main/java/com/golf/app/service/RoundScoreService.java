package com.golf.app.service;

import com.golf.app.model.RoundScore;
import java.util.Optional;

public interface RoundScoreService {

    Iterable<RoundScore> getAllRoundScores();
    Optional<RoundScore> getRoundScoreById(Long id);
    RoundScore saveRoundScore(RoundScore roundScore);
}
