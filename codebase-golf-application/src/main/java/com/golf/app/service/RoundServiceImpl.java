package com.golf.app.service;

import com.golf.app.model.Round;
import com.golf.app.repo.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    @Override
    public Iterable<Round> getAllRound() {
        return roundRepository.findAll();
    }

    @Override
    public Iterable<Round> getAllRoundByCompetitionName(String name) {
        return roundRepository.findAllByCompetitionName(name);
    }

    @Override
    public Optional<Round> getRoundById(Long id) {
        return roundRepository.findById(id);
    }

    @Override
    public Round saveRound(Round round) {
        return roundRepository.save(round);
    }

    @Override
    public void deleteRound(Long id) {
        roundRepository.deleteById(id);
    }

}
