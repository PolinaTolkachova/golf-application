package com.golf.app.service;

import com.golf.app.model.ScoreCard;

public interface ScoreCardService {

    Iterable<ScoreCard> getAllScoreCards();
    ScoreCard saveScoreCard(ScoreCard scoreCard);
}
