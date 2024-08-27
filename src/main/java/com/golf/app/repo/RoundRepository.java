package com.golf.app.repo;

import com.golf.app.model.Competition;
import com.golf.app.model.Round;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoundRepository extends CrudRepository<Round, Long> {

    Iterable<Round> findAllByCompetitionName(String name);

}
