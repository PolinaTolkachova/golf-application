package com.golf.app.api;

import com.golf.app.dto.RoundDto;
import com.golf.app.exception.CompetitionNotFoundException;
import com.golf.app.mapper.RoundMapper;
import com.golf.app.model.Competition;
import com.golf.app.service.CompetitionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class CompetitionRestController {

    private final CompetitionService competitionService;
    private final RoundMapper roundMapper;

    public CompetitionRestController(CompetitionService competitionService, RoundMapper roundMapper) {
        this.competitionService = competitionService;
        this.roundMapper = roundMapper;
    }

    @GetMapping("/{id}/rounds")
    public List<RoundDto> getCompetitionRounds(@PathVariable("id") Long id) {
        Competition competition = competitionService.getCompetitionById(id)
            .orElseThrow(() -> new CompetitionNotFoundException("Competition by ID not found"));

        return competition.getRounds().stream()
            .map(roundMapper::roundDto)
            .collect(Collectors.toList());
    }
}