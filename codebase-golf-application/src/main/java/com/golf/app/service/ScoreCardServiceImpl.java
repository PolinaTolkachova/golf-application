package com.golf.app.service;

import com.golf.app.model.ScoreCard;
import com.golf.app.repo.ScoreCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardServiceImpl implements ScoreCardService {

    private final ScoreCardRepository scoreCardRepository;

    @Autowired
    public ScoreCardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public Iterable<ScoreCard> getAllScoreCards() {
        return scoreCardRepository.findAll();
    }

    @Override
    public ScoreCard saveScoreCard(ScoreCard scoreCard) {
        return scoreCardRepository.save(scoreCard);
    }
}
