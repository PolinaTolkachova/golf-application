package com.golf.app.controller;

import com.golf.app.exception.CompetitionRoundNotFoundException;
import com.golf.app.model.Competition;
import com.golf.app.model.Round;
import com.golf.app.model.Player;
import com.golf.app.service.RoundService;
import com.golf.app.service.CompetitionService;
import com.golf.app.service.CourseService;
import com.golf.app.service.PlayerService;
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
@RequestMapping("/round")
public class RoundController {

    private static final String ROUND_MAIN_PAGE = "round/round-main";
    private static final String ROUND_ADD_PAGE = "round/round-add";
    private static final String ROUND_ADD_CHOOSE_COMPETITION_PAGE = "round/round-add-choose-competition";
    private static final String ROUND_DETAILS_PAGE = "round/round-details";
    private static final String ROUND_EDIT_PAGE = "round/round-edit";

    private static final String REDIRECT_ROUND_EDIT_PAGE = "redirect:/round/{id}/edit";
    private static final String REDIRECT_ROUND = "redirect:/round";
    private static final String REDIRECT_COMPETITION_DETAILS_PAGE = "redirect:/competition/{competitionId}";

    private static final String ROUNDS_ATTRIBUTE = "rounds";
    private static final String ROUND_ATTRIBUTE = "round";
    private static final String COMPETITIONS_NAME_LIST_ATTRIBUTE = "competitionsNameList";
    private static final String COMPETITION_ATTRIBUTE = "competition";

    private final RoundService roundService;
    private final CompetitionService competitionService;
    private final PlayerService playerService;
    private final CourseService courseService;

    @Autowired
    public RoundController(RoundService roundService,
                           CompetitionService competitionService,
                           PlayerService playerService,
                           CourseService courseService) {
        this.roundService = roundService;
        this.competitionService = competitionService;
        this.playerService = playerService;
        this.courseService = courseService;
    }

    @GetMapping
    public String displayCompetitionRoundMainPage(Model model) {
        model.addAttribute(ROUNDS_ATTRIBUTE, roundService.getAllRound());
        return ROUND_MAIN_PAGE;
    }

    @GetMapping("/add")
    public String displayRoundAddPage(Model model) {
        Iterable<Competition> competitions = competitionService.getAllCompetition();
        List<String> competitionsNameList = StreamSupport.stream(competitions.spliterator(), false)
                .map(Competition::getName)
                .collect(Collectors.toList());
        model.addAttribute(COMPETITIONS_NAME_LIST_ATTRIBUTE, competitionsNameList);
        return ROUND_ADD_CHOOSE_COMPETITION_PAGE;
    }

    @PostMapping("/add")
    public String addRound(@ModelAttribute Round round,
                           @RequestParam String competitionName) {

        Optional<Competition> competition = competitionService.getCompetitionByName(competitionName);

        List<Player> players = competition.get().getPlayers();
        List<Player> roundplayers = new ArrayList<>();
        roundplayers.addAll(players);
        round.setRoundplayers(roundplayers);

        round.setCompetition(competition.get());

        roundService.saveRound(round);
        System.out.println("round choose competition saved");

        List<Round> rounds = competition.get().getRounds();
        rounds.add(round);

        competition.get().setRounds(rounds);

        competitionService.saveCompetition(competition.get());
        System.out.println("competition saved");

        return REDIRECT_ROUND;
    }


    @GetMapping("/{competitionId}/add")
    public String displayRoundAddPageForCompetition(@PathVariable(value = "competitionId") Long competitionId,
                                                    Model model) {

        Optional<Competition> competition = competitionService.getCompetitionById(competitionId);

        model.addAttribute(COMPETITION_ATTRIBUTE, competition);
        System.out.println("comp attr");
        return ROUND_ADD_PAGE;
    }

    @PostMapping("/{competitionId}/add")
    public String addRoundForCompetition(@ModelAttribute Round round,
                                         @RequestParam String competitionName) {

        Optional<Competition> competition = competitionService.getCompetitionByName(competitionName);

        List<Player> players = competition.get().getPlayers();
        List<Player> roundplayers = new ArrayList<>();
        roundplayers.addAll(players);
        round.setRoundplayers(roundplayers);

        round.setCompetition(competition.get());

        roundService.saveRound(round);
        System.out.println("round choose competition saved");

        List<Round> rounds = competition.get().getRounds();
        rounds.add(round);

        competition.get().setRounds(rounds);

        competitionService.saveCompetition(competition.get());
        System.out.println("competition saved");

        return REDIRECT_COMPETITION_DETAILS_PAGE;
    }

    @GetMapping("/{id}")
    public String getCompetitionRoundById(@PathVariable("id") Long id, Model model) {
        Round round = roundService.getRoundById(id)
                .orElseThrow(() -> new CompetitionRoundNotFoundException("Competition round by ID not found"));

        model.addAttribute(ROUND_ATTRIBUTE, round);

        System.out.println("3");
        for (Player player : round.getRoundplayers()) {
            System.out.println(player.getName());
        }

        return ROUND_DETAILS_PAGE;
    }
}
