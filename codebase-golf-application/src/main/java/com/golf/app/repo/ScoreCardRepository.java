package com.golf.app.repo;

import com.golf.app.model.ScoreCard;
import org.springframework.data.repository.CrudRepository;

public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long> {
}
