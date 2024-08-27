package com.golf.app.repo;

import com.golf.app.model.ScoreCardImage;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ScoreCardImageRepository extends CrudRepository<ScoreCardImage, Long> {

    Optional<ScoreCardImage> findById(Long id);
}
