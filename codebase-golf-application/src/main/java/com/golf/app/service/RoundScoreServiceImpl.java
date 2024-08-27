package com.golf.app.service;

import com.golf.app.model.RoundScore;
import com.golf.app.repo.RoundScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundScoreServiceImpl implements RoundScoreService {

    private final RoundScoreRepository roundScoreRepository;

    @Autowired
    public RoundScoreServiceImpl(RoundScoreRepository roundScoreRepository) {
        this.roundScoreRepository = roundScoreRepository;
    }

    @Override
    public Iterable<RoundScore> getAllRoundScores() {
        return roundScoreRepository.findAll();
    }

    @Override
    public Optional<RoundScore> getRoundScoreById(Long id) {
        return roundScoreRepository.findById(id);
    }

    @Override
    public RoundScore saveRoundScore(RoundScore roundScore) {
        return roundScoreRepository.save(roundScore);
    }
}
