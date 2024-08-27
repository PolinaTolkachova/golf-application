package com.golf.app.repo;

import com.golf.app.model.Par;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ParRepository extends CrudRepository<Par, Long> {

    Optional<Par> findById(Long id);
}
