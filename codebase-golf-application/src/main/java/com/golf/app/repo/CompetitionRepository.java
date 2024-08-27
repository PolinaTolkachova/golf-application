package com.golf.app.repo;

import com.golf.app.model.Competition;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    Iterable<Competition> findAll(Sort sort);

    Optional<Competition> findByName(String name);

}
