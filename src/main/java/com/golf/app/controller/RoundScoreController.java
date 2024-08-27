package com.golf.app.controller;

import com.golf.app.dto.RoundScoreDto;
import com.golf.app.model.*;
import com.golf.app.service.*;
import com.golf.app.utils.ScoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/round-score")
public class RoundScoreController {

    private static final String ROUND_SCORE_INPUT_PAGE = "round-score/round-score-input-page";
    private static final String ROUND_SCORE_PAGE = "round-score/round-score-page";
    private static final String ROUND_SCORE_MAIN = "round-score/round-score-main";
    private static final String PLAYER_SURNAME_NAME_ATTRIBUTE = "playerSurnameName";
    private static final String PLAYER_ATTRIBUTE = "player";
    private static final String PLAYERS_SURNAME_NAME_LIST_ATTRIBUTE = "playersSurnameNameList";
    private static final String COMPETITIONS_ATTRIBUTE = "competitions";
    private static final String ROUND_SCORE_ATTRIBUTE = "roundScore";
    private static final String ROUNDS_ATTRIBUTE = "rounds";
    private static final String PLAYERS_ATTRIBUTE = "players";
    private static final String COMPETITION_ROUNDS_MAP_ATTRIBUTE = "competitionRoundsMap";
    private static final String ROUND_SCORE_DTO_ATTRIBUTE = "roundScoreDto";
    private static final String ROUND_PLAYERS_ATTRIBUTE = "roundPlayersMap";

    private final RoundScoreService roundScoreService;
    private final PlayerService playerService;
    private final CompetitionService competitionService;
    private final RoundService roundService;

    @Autowired
    public RoundScoreController(RoundScoreService roundScoreService, PlayerService playerService,
                                CompetitionService competitionService, RoundService roundService) {
        this.roundScoreService = roundScoreService;
        this.playerService = playerService;
        this.competitionService = competitionService;
        this.roundService = roundService;
    }

    @GetMapping
    public String displayScoreCardMainPage(Model model) {
        model.addAttribute(ROUND_SCORE_ATTRIBUTE, roundScoreService.getAllRoundScores());
        return ROUND_SCORE_MAIN;
    }

    @GetMapping("/input")
    public String displayScoreCardInputPage(Model model) {
        Iterable<Player> players = playerService.getAllPlayers();

        List<String> playersSurnameName = StreamSupport.stream(players.spliterator(), false)
            .map(player -> player.getSurname() + ", " + player.getName())
            .collect(Collectors.toList());

        Map<Competition,List<Round>> competitionRounds = new HashMap<>();
        Iterable<Competition> competitions = competitionService.getAllCompetition();
        for (Competition competition : competitions) {
            List<Round> rounds = (List<Round>) roundService.getAllRoundByCompetitionName(competition.getName());
            competitionRounds.put(competition, rounds);
        }

        Map<Round, List<Player>> roundPlayers = new HashMap<>();
        Iterable<Round> rounds = roundService.getAllRound();
        for (Round round : rounds) {
            List<Player> playersByRound = (List<Player>) playerService.getAllPlayersByRound(round.getName());
            roundPlayers.put(round, playersByRound);
        }

        model.addAttribute(ROUND_PLAYERS_ATTRIBUTE, roundPlayers);
        model.addAttribute(COMPETITION_ROUNDS_MAP_ATTRIBUTE, competitionRounds);
        model.addAttribute(PLAYER_SURNAME_NAME_ATTRIBUTE, playersSurnameName);
        model.addAttribute(PLAYERS_ATTRIBUTE, players);
        model.addAttribute(COMPETITIONS_ATTRIBUTE, competitions);
        model.addAttribute(ROUNDS_ATTRIBUTE, rounds);

        return ROUND_SCORE_INPUT_PAGE;
    }

    @PostMapping("/submit")
    public String addScoreCard(@ModelAttribute RoundScoreDto roundScoreDto) {
        RoundScore roundScore = new RoundScore();
        String[] names = roundScoreDto.getPlayerSurnameName().split(", ");
        String playerSurname = names[0];
        String playerName = names[1];
        Optional<Player> chosenPlayer = playerService
                .getPlayerBySurnameAndName(playerSurname, playerName);
        roundScore.setPlayer(chosenPlayer.get());

        Optional<Competition> chosenCompetition = competitionService
                .getCompetitionByName(roundScoreDto.getCompetitionName());
        roundScore.setCompetition(chosenCompetition.get());

        roundScore.setDate(roundScoreDto.getDate());

        roundScore.setScores(ScoreUtils.buildScores(roundScoreDto));
        roundScoreService.saveRoundScore(roundScore);

        if (true) {
            roundScoreService.saveRoundScore(ScoreUtils.roundScoreCalculator(roundScore));
        }
        return "redirect:/round-score/" + roundScore.getId();
    }

    @GetMapping("/{id}")
    public String displayScoreCardPage(@PathVariable Long id, Model model) {
        Optional<RoundScore> roundScore = roundScoreService.getRoundScoreById(id);

        model.addAttribute(PLAYER_ATTRIBUTE, roundScore.get().getPlayer());
        model.addAttribute(PLAYER_SURNAME_NAME_ATTRIBUTE, roundScore.get().getPlayer().getSurname() + " " +
                roundScore.get().getPlayer().getName());
        model.addAttribute(ROUND_SCORE_ATTRIBUTE, roundScore.get());

        return ROUND_SCORE_PAGE;
    }
}
