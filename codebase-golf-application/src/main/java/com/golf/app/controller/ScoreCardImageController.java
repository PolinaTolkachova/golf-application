package com.golf.app.controller;

import com.golf.app.dto.ImageDto;
import com.golf.app.model.ScoreCardImage;
import com.golf.app.model.Player;
import com.golf.app.service.ScoreCardImageServiceImpl;
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
@RequestMapping("/score-card-image")
public class ScoreCardImageController {

    private static final String UPLOAD_SCORE_CARD_IMAGE_PAGE = "score-card-image/score-card-image-upload";
    private static final String UPLOAD_IMAGE_PAGE_PLAYER = "image/upload-image-player";
    private static final String DISPLAY_SCORE_CARD_IMAGE_PAGE = "score-card-image/display-image";
    private static final String DISPLAY_IMAGE_LIST_PAGE = "player-photo/player-photo-list";
    private static final String REDIRECT = "redirect:/";
    private static final String PLAYER_DETAILS_PAGE = "player/{id}";
    private static final String REDIRECT_PLAYER_DETAILS_PAGE = "redirect:/player/{id}";

    private final ScoreCardImageServiceImpl scoreCardImageService;
    private final PlayerService playerService;

    @Autowired
    public ScoreCardImageController(ScoreCardImageServiceImpl scoreCardImageService,
                                    PlayerService playerService) {
        this.scoreCardImageService = scoreCardImageService;
        this.playerService = playerService;
    }

    @GetMapping("/upload")
    public String displayUploadImagePage() {
        return UPLOAD_SCORE_CARD_IMAGE_PAGE;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        ScoreCardImage scoreCardImage = new ScoreCardImage();
        scoreCardImage.setName(file.getOriginalFilename());
        scoreCardImage.setData(file.getBytes());

        scoreCardImageService.saveScoreCardImage(scoreCardImage);
        return REDIRECT;

    }

    @GetMapping("/upload/{id}/player-photo-upload")
    public String displayUploadImagePageForPlayer(){
        return UPLOAD_IMAGE_PAGE_PLAYER;
    }

    @PostMapping("/upload/{id}/player-photo-upload")
    public String uploadImageForPlayer(@PathVariable("id") Long id,
                                       @RequestParam("file") MultipartFile file) throws IOException {
        ScoreCardImage scoreCardImage = new ScoreCardImage();
        scoreCardImage.setName(file.getOriginalFilename());
        scoreCardImage.setData(file.getBytes());

        scoreCardImageService.saveScoreCardImage(scoreCardImage);

        Long photoId = scoreCardImage.getId();

        Optional<Player> player = playerService.getPlayerById(id);
        player.ifPresent(p -> p.setPhotoId(photoId));
        playerService.savePlayer(player.get());

        return REDIRECT_PLAYER_DETAILS_PAGE;
    }

    @GetMapping()
    public String displayAllImages(Model model) {
        List<ScoreCardImage> scoreCardImages = scoreCardImageService.findAllScoreCardImages();
        List<ImageDto> imageDTOs = scoreCardImages.stream()
            .map(image -> new ImageDto(image.getId(), image.getName(),
                    Base64.getEncoder().encodeToString(image.getData())))
            .collect(Collectors.toList());

        model.addAttribute("imageDTOs", imageDTOs);
        return DISPLAY_IMAGE_LIST_PAGE;
    }

    @GetMapping("/display")
    public String showDisplayImagePage() {
        return DISPLAY_SCORE_CARD_IMAGE_PAGE;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        ScoreCardImage scoreCardImage = scoreCardImageService.findScoreCardImageById(id).get();
        return new ResponseEntity<>(scoreCardImage.getData(), headers, HttpStatus.OK);
    }
}
