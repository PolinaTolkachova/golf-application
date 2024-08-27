package com.golf.app.repo;

import com.golf.app.model.PlayerPhoto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerPhotoRepository extends CrudRepository<PlayerPhoto, Long> {

    Optional<PlayerPhoto> findById(Long id);
}
