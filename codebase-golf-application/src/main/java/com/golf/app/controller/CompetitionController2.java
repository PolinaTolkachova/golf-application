package com.golf.app.controller;

import com.golf.app.dto.RoundDto;
import com.golf.app.exception.CompetitionNotFoundException;
import com.golf.app.exception.PlayerAlreadyRegistedException;
import com.golf.app.exception.PlayerNotFoundException;
import com.golf.app.model.Competition;
import com.golf.app.model.Course;
import com.golf.app.model.Player;
import com.golf.app.model.Round;
import com.golf.app.service.CompetitionService;
import com.golf.app.service.CourseService;
import com.golf.app.service.PlayerService;
import com.golf.app.service.RoundService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/competition")
public class CompetitionController2 {

    private static final String COMPETITION_MAIN_PAGE = "competition/competition-main";
    private static final String COMPETITION_ADD_PAGE = "competition/competition-add";
    private static final String COMPETITION_DETAILS_PAGE = "competition/competition-details";
    private static final String COMPETITION_EDIT_PAGE = "competition/competition-edit";
    private static final String COMPETITION_ATTRIBUTE = "competition";
    private static final String COURSE_NAME_LIST_ATTRIBUTE = "courseNameList";
    private static final String PLAYERS_SURNAME_NAME_LIST_ATTRIBUTE = "playersSurnameNameList";
    private static final String ROUND_ADD_PAGE = "round/round-add";
    private static final String REDIRECT_COMPETITION = "redirect:/competition";
    private static final String REDIRECT_COMPETITION_EDIT_PAGE = "redirect:/competition/{id}/edit";
    private static final String REDIRECT_COMPETITION_DETAILS_PAGE = "redirect:/competition/{id}";
    private static final String REDIRECT_ROUND_DETAILS_PAGE = "redirect:/round/{roundId}";

    private final CompetitionService competitionService;
    private final RoundService roundService;
    private final PlayerService playerService;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionController2(CompetitionService competitionService, RoundService roundService,
                                  PlayerService playerService, CourseService courseService, ModelMapper modelMapper) {
        this.competitionService = competitionService;
        this.roundService = roundService;
        this.playerService = playerService;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String displayCompetitionMainPage(Model model) {
        model.addAttribute(COMPETITION_ATTRIBUTE, competitionService.getAllCompetition());
        return COMPETITION_MAIN_PAGE;
    }

    @GetMapping("/add")
    public String displayCompetitionAddPage(Model model) {
        Iterable<Course> courses = courseService.getAllCourses();
        List<String> coursesNameList = StreamSupport.stream(courses.spliterator(), false)
                .map(Course::getName)
                .collect(Collectors.toList());
        model.addAttribute(COURSE_NAME_LIST_ATTRIBUTE, coursesNameList);
        return COMPETITION_ADD_PAGE;
    }

    @PostMapping("/add")
    public String addCompetition(@ModelAttribute Competition competition,
                                 @RequestParam String courseName) {

        Optional<Course> course = courseService.getCourseByName(courseName);
        competition.setCourse(course.get());

        competitionService.saveCompetition(competition);
        return REDIRECT_COMPETITION;
    }

    @GetMapping("/{id}")
    public String getCompetitionById(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));

        model.addAttribute(COMPETITION_ATTRIBUTE, competition);
        return COMPETITION_DETAILS_PAGE;
    }

    @GetMapping("/{id}/edit")
    public String displayCompetitionEditPage(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));
        Iterable<Player> players = playerService.getAllPlayers();
        List<String> playersSurnameNameList = StreamSupport.stream(players.spliterator(), false)
                .map(player -> player.getSurname() + ", " + player.getName())
                .collect(Collectors.toList());
        Iterable<Course> courses = courseService.getAllCourses();
        List<String> coursesNameList = StreamSupport.stream(courses.spliterator(), false)
                .map(Course::getName)
                .collect(Collectors.toList());
        model.addAttribute(COMPETITION_ATTRIBUTE, competition);
        model.addAttribute(COURSE_NAME_LIST_ATTRIBUTE, coursesNameList);
        model.addAttribute(PLAYERS_SURNAME_NAME_LIST_ATTRIBUTE, playersSurnameNameList);
        return COMPETITION_EDIT_PAGE;
    }

    @PostMapping("/{id}/edit")
    public String updateCompetition(@PathVariable("id") Long id,
                                    @ModelAttribute Competition competition,
                                    @RequestParam String playerSurnameName,
                                    @RequestParam String courseName,
                                    Model model) {

        Optional<Course> course = courseService.getCourseByName(courseName);
        competition.setCourse(course.get());

        List<Player> registeredPlayers = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"))
                .getPlayers();

        List<Round> roundList = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"))
                .getRounds();

        if (playerSurnameName != "") {
            String[] names = playerSurnameName.split(", ");
            String playerSurname = names[0];
            String playerName = names[1];
            Optional<Player> player = Optional.ofNullable(playerService.getPlayerBySurnameAndName(playerSurname,
                    playerName)
                    .orElseThrow(() -> new PlayerNotFoundException("Player " + playerSurname + " " +
                        playerName + " not found")));

            boolean isPlayerAlreadyRegistered = registeredPlayers.stream()
                    .anyMatch(p -> p.getSurname().equals(playerSurname) && p.getName().equals(playerName));
            if (!isPlayerAlreadyRegistered) {
                registeredPlayers.add(player.get());
            } else {
                throw new PlayerAlreadyRegistedException("Player " + playerSurname + " " + playerName +
                    " has been already registered");
            }
        }
        competition.setPlayers(registeredPlayers);
        competition.setRounds(roundList);
        competitionService.saveCompetition(competition);

        model.addAttribute(COMPETITION_ATTRIBUTE, competition);
        return COMPETITION_DETAILS_PAGE;
    }

    @GetMapping("/{id}/add-round")
    public String getCompetitionRoundAddPageForCompetitionById(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));

        model.addAttribute(COMPETITION_ATTRIBUTE, competition);
        System.out.println("333");
        return ROUND_ADD_PAGE;
    }

    @PostMapping("/{id}/add-round")
    public String addCompetition(@ModelAttribute Round round,
                                 @RequestParam String competitionName) {

        Optional<Competition> competition = competitionService.getCompetitionByName(competitionName);

        List<Player> roundPlayers = competition.get().getPlayers();
        round.setRoundplayers(roundPlayers);

        round.setCompetition(competition.get());

        roundService.saveRound(round);

        for (Player player : round.getRoundplayers()) {
            System.out.println(player.getName());
        }

        List<Round> rounds = competition.get().getRounds();
        rounds.add(round);

        competitionService.saveCompetition(competition.get());
        System.out.println("competition saved");

        return REDIRECT_COMPETITION_DETAILS_PAGE;
    }

    @GetMapping("/{id}/rounds")
    public List<Round> getCompetitionRounds(@PathVariable("id") Long id, Model model) {
        Competition competition = competitionService.getCompetitionById(id)
                .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));

        List<Round> rounds = competition.getRounds();
        return rounds;
    }

    @GetMapping("data/{id}/round")
    public List<RoundDto> getCompetitionRounds(@PathVariable("id") Long id) {
        Competition competition = competitionService.getCompetitionById(id)
            .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));

        List<RoundDto> roundDtos = competition.getRounds().stream()
            .map(this::convertToRoundDto)
            .collect(Collectors.toList());

        return roundDtos;
    }

    protected RoundDto convertToRoundDto(Round round) {
        RoundDto roundDto = new RoundDto();
        roundDto.setId(round.getId());
        roundDto.setRoundName(round.getName());
        return roundDto;
    }
}
