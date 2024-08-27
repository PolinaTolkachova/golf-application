package com.golf.app.service;

import com.golf.app.model.PlayerPhoto;

import java.util.List;
import java.util.Optional;

public interface PlayerPhotoService {

    void savePlayerPhoto(PlayerPhoto playerPhoto);
    Optional<PlayerPhoto> findPlayerPhotoById(Long id);
    List<PlayerPhoto> findAllPlayerPhotos();
}
