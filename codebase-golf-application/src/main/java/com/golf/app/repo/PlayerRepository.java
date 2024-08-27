package com.golf.app.repo;

import com.golf.app.model.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findAll(Sort sort );
    Optional<Player> getByName(String name);
    Optional<Player> getBySurnameAndName(String surname, String name);
    Iterable<Player> findAllByRoundName(String name, Sort sort);
}
