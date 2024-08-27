package com.golf.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class RoundScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;
    private LocalDate date;
    @ManyToOne
    private Competition competition;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "round_id")
    private Round round;
    @OneToOne
    private ScoreCard scoreCard;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Score> scores;
    private Integer sumStroke;
    private Integer sumPenalty;
    private Integer sumPar;
    private Integer sumGrossScore;
    private Integer sumNetScore;
    private Integer sumNetScorePar;
    private Integer sumStableford;
    private Integer sumScoreSubtractPar;
    private Integer sumScoreSubtractParHcp;
}
