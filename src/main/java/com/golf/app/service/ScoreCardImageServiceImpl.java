package com.golf.app.service;

import com.golf.app.model.ScoreCardImage;
import com.golf.app.repo.ScoreCardImageRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardImageServiceImpl implements ScoreCardImageService {

    private ScoreCardImageRepository scoreCardImageRepository;

    @Autowired
    public ScoreCardImageServiceImpl(ScoreCardImageRepository scoreCardImageRepository) {
        this.scoreCardImageRepository = scoreCardImageRepository;
    }

    public void saveScoreCardImage(ScoreCardImage scoreCardImage) {
        scoreCardImageRepository.save(scoreCardImage);
    }

    public Optional<ScoreCardImage> findScoreCardImageById(Long id) {
        return scoreCardImageRepository.findById(id);
    }

    @Override
    public List<ScoreCardImage> findAllScoreCardImages() {
        return (List<ScoreCardImage>) scoreCardImageRepository.findAll();
    }
    public byte[] getImageContentById(Long id) {
        Optional<ScoreCardImage> optionalImage = scoreCardImageRepository.findById(id);

        if (optionalImage.isPresent()) {
            return optionalImage.get().getData();
        } else {
            return null;
        }
    }

}
