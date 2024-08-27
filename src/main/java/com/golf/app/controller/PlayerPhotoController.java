package com.golf.app.controller;

import com.golf.app.dto.PlayerPhotoDto;
import com.golf.app.model.Player;
import com.golf.app.model.PlayerPhoto;
import com.golf.app.service.PlayerPhotoServiceImpl;
import com.golf.app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/player-photo")
public class PlayerPhotoController {

    private static final String UPLOAD_PLAYER_PHOTO_PAGE = "player-photo/player-photo-upload";
    private static final String UPLOAD_IMAGE_PAGE_PLAYER = "image/upload-image-player";
    private static final String DISPLAY_IMAGE_PAGE = "image/display-image";
    private static final String DISPLAY_PLAYER_PHOTO_LIST_PAGE = "player-photo/player-photo-list";
    private static final String REDIRECT = "redirect:/";
    private static final String PLAYER_DETAILS_PAGE = "player/{id}";
    private static final String REDIRECT_PLAYER_DETAILS_PAGE = "redirect:/player/{id}";

    private final PlayerPhotoServiceImpl playerPhotoService;
    private final PlayerService playerService;

    @Autowired
    public PlayerPhotoController(PlayerPhotoServiceImpl playerPhotoService, PlayerService playerService) {
        this.playerPhotoService = playerPhotoService;
        this.playerService = playerService;
    }

    @GetMapping("/upload")
    public String displayUploadImagePage() {
        return UPLOAD_PLAYER_PHOTO_PAGE;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        PlayerPhoto playerPhoto = new PlayerPhoto();
        playerPhoto.setName(file.getOriginalFilename());
        playerPhoto.setData(file.getBytes());

        playerPhotoService.saveImage(playerPhoto);
        return REDIRECT;

    }

    @GetMapping("/upload/{id}/player-photo-upload")
    public String displayUploadImagePageForPlayer(){
        return UPLOAD_PLAYER_PHOTO_PAGE;
    }

    @PostMapping("/upload/{id}/player-photo-upload")
    public String uploadImageForPlayer(@PathVariable("id") Long id,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        PlayerPhoto playerPhoto = new PlayerPhoto();
        playerPhoto.setName(file.getOriginalFilename());
        playerPhoto.setData(file.getBytes());

        playerPhotoService.saveImage(playerPhoto);

        Long photoId = playerPhoto.getId();

        Optional<Player> player = playerService.getPlayerById(id);
        player.ifPresent(p -> p.setPhotoId(photoId));
        playerService.savePlayer(player.get());

        return REDIRECT_PLAYER_DETAILS_PAGE;
    }

    @GetMapping()
    public String displayAllPlayerPhotos(Model model) {
        List<PlayerPhoto> playerPhotos = playerPhotoService.findAllPlayerPhotos();
        List<PlayerPhotoDto> playerPhotoDTOs = playerPhotos.stream()
            .map(playerPhoto -> new PlayerPhotoDto(playerPhoto.getId(), playerPhoto.getName(),
                    Base64.getEncoder().encodeToString(playerPhoto.getData())))
            .collect(Collectors.toList());

        model.addAttribute("playerPhotoDTOs", playerPhotoDTOs);
        return DISPLAY_PLAYER_PHOTO_LIST_PAGE;
    }

    @GetMapping("/player-photo/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        PlayerPhoto playerPhoto = playerPhotoService.findImageById(id).get();
        return new ResponseEntity<>(playerPhoto.getData(), headers, HttpStatus.OK);
    }
}
