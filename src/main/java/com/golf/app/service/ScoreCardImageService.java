package com.golf.app.service;

import com.golf.app.model.ScoreCardImage;

import java.util.List;
import java.util.Optional;

public interface ScoreCardImageService {

    void saveScoreCardImage(ScoreCardImage scoreCardImage);
    Optional<ScoreCardImage> findScoreCardImageById(Long id);
    List<ScoreCardImage> findAllScoreCardImages();
}
