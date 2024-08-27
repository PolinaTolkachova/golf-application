package com.golf.app.service;

import com.golf.app.model.PlayerPhoto;
import com.golf.app.repo.PlayerPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerPhotoServiceImpl implements PlayerPhotoService {

    private PlayerPhotoRepository playerPhotoRepository;

    @Autowired
    public PlayerPhotoServiceImpl(PlayerPhotoRepository playerPhotoRepository) {
        this.playerPhotoRepository = playerPhotoRepository;
    }

    public void saveImage(PlayerPhoto playerPhoto) {
        playerPhotoRepository.save(playerPhoto);
    }

    public Optional<PlayerPhoto> findImageById(Long id) {
        return playerPhotoRepository.findById(id);
    }

    @Override
    public void savePlayerPhoto(PlayerPhoto playerPhoto) {

    }

    @Override
    public Optional<PlayerPhoto> findPlayerPhotoById(Long id) {
        return playerPhotoRepository.findById(id);
    }

    @Override
    public List<PlayerPhoto> findAllPlayerPhotos() {
        return (List<PlayerPhoto>) playerPhotoRepository.findAll();
    }
    public byte[] getPlayerPhotoContentById(Long id) {
        Optional<PlayerPhoto> optionalPlayerPhoto = playerPhotoRepository.findById(id);

        if (optionalPlayerPhoto.isPresent()) {
            return optionalPlayerPhoto.get().getData();
        } else {
            return null;
        }
    }

}
