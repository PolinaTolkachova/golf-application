package com.golf.app.dto;

import com.golf.app.model.Course;
import com.golf.app.model.Judge;
import com.golf.app.model.Player;
import com.golf.app.model.Team;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionDto {

    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private Course course;
    private List<Judge> judges;
    private List<Player> players;
    private List<Team> teams;
}
