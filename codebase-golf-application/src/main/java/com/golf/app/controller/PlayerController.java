package com.golf.app.controller;

import com.golf.app.dto.PlayerPhotoDto;
import com.golf.app.exception.PlayerNotFoundException;
import com.golf.app.model.Player;
import com.golf.app.model.PlayerPhoto;
import com.golf.app.service.PlayerPhotoService;
import com.golf.app.service.PlayerService;
import com.golf.app.utils.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a controller class for Client entity.
 * It provides methods for CRUD operations with Client entity.
 */
@Controller
@RequestMapping("/player")
public class PlayerController {

    private static final Logger LOGGER = Logger.getLogger(PlayerController.class.getName());

    private static final String PLAYER_MAIN_PAGE = "player/player-main";
    private static final String PLAYER_ADD_PAGE = "player/player-add";
    private static final String PLAYER_DETAILS_PAGE = "player/player-details";
    private static final String PLAYER_EDIT_PAGE = "player/player-edit";
    private static final String PLAYER_ATTRIBUTE = "player";
    private static final String PLAYER_PHOTO_DTO_ATTRIBUTE = "playerPhotoDto";
    private static final String PLAYERS_ATTRIBUTE = "players";
    private static final String REDIRECT_TO_PLAYER = "redirect:/player";
    private static final String ERRORS_ATTRIBUTE = "errors";

    private final PlayerService playerService;
    private final PlayerPhotoService playerPhotoService;

    @Autowired
    public PlayerController(PlayerService playerService,
                            PlayerPhotoService playerPhotoService) {
        this.playerService = playerService;
        this.playerPhotoService = playerPhotoService;
    }

    /**
     * This method returns all players from database.
     *
     * @param model {@link Model}
     * @return PLAYER_MAIN_PAGE
     */
    @GetMapping
    public String displayPlayerMainPage(Model model) {
        model.addAttribute(PLAYERS_ATTRIBUTE, playerService.getAllPlayers());
        return PLAYER_MAIN_PAGE;
    }

    /**
     * This method returns PLAYER_ADD_PAGE
     */
    @GetMapping("/add")
    public String displayPlayerAddPage() {
        return PLAYER_ADD_PAGE;
    }

    /**
     * This method save a new player to database.
     *
     * @param player {@link Player}
     * @return REDIRECT_TO_PLAYER
     */
    @PostMapping("/add")
    public String addPlayer(@ModelAttribute @Valid Player player, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(PLAYER_ATTRIBUTE, player);
            model.addAttribute(ERRORS_ATTRIBUTE, ValidationUtils.getFieldErrors(bindingResult));
            return PLAYER_ADD_PAGE;
        }
        player.setPhotoId(1L);
        playerService.savePlayer(player);
        LOGGER.log(Level.INFO, "Player " + player.getSurname() + " " + player.getName()
                + " with ID " + player.getId() + " has been saved in the DB");
        return REDIRECT_TO_PLAYER;
    }

    /**
     * This method returns get player by id from database.
     *
     * @param id    {@link Long}
     * @param model {@link Model}
     * @return PLAYER_DETAILS_PAGE
     */
    @GetMapping("/{id}")
    public String displayPlayerDetailsPage(@PathVariable("id") Long id, Model model) {
        Player player = playerService.getPlayerById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player by ID not found"));

        Optional<PlayerPhoto> playerPhoto = playerPhotoService.findPlayerPhotoById(player.getPhotoId());
        PlayerPhotoDto playerPhotoDto = null;
        if (playerPhoto.isPresent()) {
            playerPhotoDto = new PlayerPhotoDto(playerPhoto.get().getId(), playerPhoto.get().getName(), Base64.getEncoder().encodeToString(playerPhoto.get().getData()));
        } else {
            playerPhoto = playerPhotoService.findPlayerPhotoById(1L);
            playerPhotoDto = new PlayerPhotoDto(playerPhoto.get().getId(), playerPhoto.get().getName(), Base64.getEncoder().encodeToString(playerPhoto.get().getData()));

        }

        model.addAttribute(PLAYER_ATTRIBUTE, player);
        model.addAttribute(PLAYER_PHOTO_DTO_ATTRIBUTE, playerPhotoDto);
        return PLAYER_DETAILS_PAGE;
    }

    /**
     * This method returns PLAYER_EDIT_PAGE
     *
     * @param id    {@link Player} id
     * @param model {@link Model}
     * @return PLAYER_EDIT_PAGE
     */
    @GetMapping("/{id}/edit")
    public String displayPlayerEditPage(@PathVariable("id") Long id, Model model) {
        Player player = playerService.getPlayerById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player by ID not found"));

        model.addAttribute(PLAYER_ATTRIBUTE, player);
        return PLAYER_EDIT_PAGE;
    }

    /**
     * This method updates player data.
     *
     * @param player {@link Player}
     * @return PLAYER_DETAILS_PAGE
     */
    @PostMapping("/{id}/edit")
    public String updatePlayer(@ModelAttribute Player player, Model model) {
        playerService.savePlayer(player);
        LOGGER.log(Level.INFO, "Player " + player.getSurname() + " " + player.getName() + " with ID " +
            player.getId() + " has been updated in the DB");

        model.addAttribute(PLAYER_ATTRIBUTE, player);
        return PLAYER_DETAILS_PAGE;
    }

    /**
     * This method deletes a player from database.
     *
     * @param id {@link Long}
     * @return REDIRECT_TO_PLAYER
     */
    @PostMapping("/{id}/remove")
    public String deletePlayer(@PathVariable("id") Long id) {
        Player player = playerService.getPlayerById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player by ID not found"));
        playerService.deletePlayer(id);
        LOGGER.log(Level.INFO, "Player " + player.getSurname() + " " + player.getName() + " with ID " +
            player.getId() + " was REMOVED from DB");
        return REDIRECT_TO_PLAYER;
    }
}