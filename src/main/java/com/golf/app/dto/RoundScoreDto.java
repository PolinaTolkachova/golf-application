package com.golf.app.dto;

import com.golf.app.model.*;

import java.time.LocalDate;
import java.util.List;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoundScoreDto {

    private Player player;
    private LocalDate date;
    private String competitionName;
    private String playerSurnameName;
    private List<Integer> strokes;
    private List<Integer> penalties;
    private Map<Round, List<Player>> roundPlayers;
    private Map<Competition,List<Round>> competitionRounds;
    private List<String> playersSurnameName;
    private Iterable<Player> players;
    private Iterable<Competition> competitions;
    private Iterable<Round> rounds;
}
