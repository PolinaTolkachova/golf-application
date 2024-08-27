package com.golf.app.controller;

import com.golf.app.model.Competition;
import com.golf.app.model.Player;
import com.golf.app.model.RoundScore;
import com.golf.app.model.Score;
import com.golf.app.service.CompetitionService;
import com.golf.app.service.ParService;
import com.golf.app.service.PlayerService;
import com.golf.app.service.RoundScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/score-card")
public class ScoreCardController {

    private static final String ROUND_SCORE_INPUT_PAGE = "round-score/round-score-input-page";
    private static final String ROUND_SCORE_PAGE = "round-score/round-score-page";
    private static final String ROUND_SCORE_MAIN = "round-score/round-score-main";
    private static final String PLAYER_SURNAME_NAME_ATTRIBUTE = "playerSurnameName";
    private static final String PLAYER_ATTRIBUTE = "player";
    private static final String PLAYERS_SURNAME_NAME_LIST_ATTRIBUTE = "playersSurnameNameList";
    private static final String COMPETITIONS_ATTRIBUTE = "competitions";
    private static final String ROUND_SCORE_ATTRIBUTE = "roundScore";
    private static final String ROUND_SCORE_DTO_ATTRIBUTE = "roundScoreDto";

    private final RoundScoreService roundScoreService;
    private final PlayerService playerService;
    private final CompetitionService competitionService;
    private final ParService parService;

    @Autowired
    public ScoreCardController(RoundScoreService roundScoreService, PlayerService playerService,
                               CompetitionService competitionService, ParService parService) {
        this.roundScoreService = roundScoreService;
        this.playerService = playerService;
        this.competitionService = competitionService;
        this.parService = parService;
    }

    @GetMapping
    public String displayScoreCardMainPage(Model model) {
        model.addAttribute(ROUND_SCORE_ATTRIBUTE, roundScoreService.getAllRoundScores());
        return ROUND_SCORE_MAIN;
    }

    @GetMapping("/input")
    public String displayScoreCardInputPage(Model model) {
        Iterable<Player> players = playerService.getAllPlayers();

        List<String> playersSurnameNameList = StreamSupport.stream(players.spliterator(), false)
                .map(player -> player.getSurname() + ", " + player.getName())
                .collect(Collectors.toList());

        model.addAttribute(PLAYERS_SURNAME_NAME_LIST_ATTRIBUTE, playersSurnameNameList);
        model.addAttribute(COMPETITIONS_ATTRIBUTE, competitionService.getAllCompetition());

        return ROUND_SCORE_INPUT_PAGE;
    }

    @PostMapping("/submit")
    public String addScoreCard(@ModelAttribute RoundScore roundScore,
                               @RequestParam String competitionName,
                               @RequestParam String playerSurnameName,
                               @RequestParam("score01") Integer scoreInt01,
                               @RequestParam("score02") Integer scoreInt02,
                               @RequestParam("score03") Integer scoreInt03,
                               @RequestParam("score04") Integer scoreInt04,
                               @RequestParam("score05") Integer scoreInt05,
                               @RequestParam("score06") Integer scoreInt06,
                               @RequestParam("score07") Integer scoreInt07,
                               @RequestParam("score08") Integer scoreInt08,
                               @RequestParam("score09") Integer scoreInt09,
                               @RequestParam("score10") Integer scoreInt10,
                               @RequestParam("score11") Integer scoreInt11,
                               @RequestParam("score12") Integer scoreInt12,
                               @RequestParam("score13") Integer scoreInt13,
                               @RequestParam("score14") Integer scoreInt14,
                               @RequestParam("score15") Integer scoreInt15,
                               @RequestParam("score16") Integer scoreInt16,
                               @RequestParam("score17") Integer scoreInt17,
                               @RequestParam("score18") Integer scoreInt18
                               ) {
        String[] names = playerSurnameName.split(", ");
        String playerSurname = names[0];
        String playerName = names[1];
        Optional<Player> chosenPlayer = playerService.getPlayerBySurnameAndName(playerSurname, playerName);
        roundScore.setPlayer(chosenPlayer.get());

        Optional<Competition> chosenCompetition = competitionService.getCompetitionByName(competitionName);
        roundScore.setCompetition(chosenCompetition.get());

        List<Score> scores = new ArrayList<>();
        Score score01 = new Score();
        score01.setStroke(scoreInt01);
        scores.add(score01);

        Score score02 = new Score();
        score02.setStroke(scoreInt02);
        scores.add(score02);

        Score score03 = new Score();
        score03.setStroke(scoreInt03);
        scores.add(score03);

        Score score04 = new Score();
        score04.setStroke(scoreInt04);
        scores.add(score04);

        Score score05 = new Score();
        score05.setStroke(scoreInt05);
        scores.add(score05);

        Score score06 = new Score();
        score06.setStroke(scoreInt06);
        scores.add(score06);

        Score score07 = new Score();
        score07.setStroke(scoreInt07);
        scores.add(score07);

        Score score08 = new Score();
        score08.setStroke(scoreInt08);
        scores.add(score08);

        Score score09 = new Score();
        score09.setStroke(scoreInt09);
        scores.add(score09);

        Score score10 = new Score();
        score10.setStroke(scoreInt10);
        scores.add(score10);

        Score score11 = new Score();
        score11.setStroke(scoreInt11);
        scores.add(score11);

        Score score12 = new Score();
        score12.setStroke(scoreInt12);
        scores.add(score12);

        Score score13 = new Score();
        score13.setStroke(scoreInt13);
        scores.add(score13);

        Score score14 = new Score();
        score14.setStroke(scoreInt14);
        scores.add(score14);

        Score score15 = new Score();
        score15.setStroke(scoreInt15);
        scores.add(score15);

        Score score16 = new Score();
        score16.setStroke(scoreInt16);
        scores.add(score16);

        Score score17 = new Score();
        score17.setStroke(scoreInt17);
        scores.add(score17);

        Score score18 = new Score();
        score18.setStroke(scoreInt18);
        scores.add(score18);

        roundScore.setScores(scores);
        roundScoreService.saveRoundScore(roundScore);

        return "redirect:/round-score/" + roundScore.getId();
    }

    @GetMapping("/{id}")
    public String displayScoreCardPage(@PathVariable Long id, Model model) {
        Optional<RoundScore> roundScore = roundScoreService.getRoundScoreById(id);
        List<Score> scores = roundScore.get().getScores();
        Integer playerHcp = Math.toIntExact(Math.round(roundScore.get().getPlayer().getHcp()));
        int sumScore = 0;
        int sumScoreSubtractPar = 0;
        int sumScoreSubtractParHcp = 0;
        int sumStableford = 0;
        for (Score score : scores) {
            sumScore = sumScore + score.getStroke();
            roundScore.get().setSumStroke(sumScore);
            score.setScoreSubtractPar(score.getStroke() - roundScore.get().getCompetition().getCourse().getHoles().get(scores.indexOf(score)).getPar());
            sumScoreSubtractPar = sumScoreSubtractPar + score.getScoreSubtractPar();
            roundScore.get().setSumScoreSubtractPar(sumScoreSubtractPar);
            Integer strokeIndex = roundScore.get().getCompetition().getCourse().getHoles().get(scores.indexOf(score)).getStrokeIndex();
            Integer scoreToCorrect = playerHcp % roundScore.get().getCompetition().getCourse().getHoles().size() - strokeIndex;
            int par2 = roundScore.get().getCompetition().getCourse().getHoles().get(scores.indexOf(score)).getPar();
            if (scoreToCorrect >= 0) {
                int allHolesCorrection = playerHcp / scores.size();
                scoreToCorrect = allHolesCorrection + 1;
                score.setScoreSubtractParHcp(score.getStroke() - par2 - scoreToCorrect);
            } else {
                int allHolesCorrection = playerHcp / scores.size();
                scoreToCorrect = allHolesCorrection;
                score.setScoreSubtractParHcp(score.getStroke() - par2 - scoreToCorrect);
            }
            int scoreSubtractParHcp = score.getScoreSubtractParHcp();
            boolean playedInPar = scoreSubtractParHcp == 0;
            sumScoreSubtractParHcp = sumScoreSubtractParHcp + scoreSubtractParHcp;
            roundScore.get().setSumScoreSubtractParHcp(sumScoreSubtractParHcp);
            if (scoreSubtractParHcp >= 2) {
                score.setStableford(0);
            } else if (scoreSubtractParHcp == 1) {
                score.setStableford(1);
            } else if (playedInPar) {
                score.setStableford(2);
            } else if (scoreSubtractParHcp == -1) {
                score.setStableford(3);
            } else if (scoreSubtractParHcp == -2) {
                score.setStableford(4);
            } else if (scoreSubtractParHcp == -3) {
                score.setStableford(5);
            }
            sumStableford = sumStableford + score.getStableford();
            roundScore.get().setSumStableford(sumStableford);
        }

        roundScoreService.saveRoundScore(roundScore.get());

        model.addAttribute(PLAYER_ATTRIBUTE, roundScore.get().getPlayer());
        model.addAttribute(PLAYER_SURNAME_NAME_ATTRIBUTE, roundScore.get().getPlayer().getSurname() + " " +
                roundScore.get().getPlayer().getName());
        model.addAttribute(ROUND_SCORE_ATTRIBUTE, roundScore.get());

        return ROUND_SCORE_PAGE;
    }
}
