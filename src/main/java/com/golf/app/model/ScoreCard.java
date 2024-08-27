package com.golf.app.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player player;
    private LocalDate date;
    private Long courseId;
    private Long tournamentId;
    private Double hcp;
    private Integer hole1;
    private Integer hole2;
    private Integer hole3;
    private Integer hole4;
    private Integer hole5;
    private Integer hole6;
    private Integer hole7;
    private Integer hole8;
    private Integer hole9;
    private Integer hole10;
    private Integer hole11;
    private Integer hole12;
    private Integer hole13;
    private Integer hole14;
    private Integer hole15;
    private Integer hole16;
    private Integer hole17;
    private Integer hole18;
    private Integer totalScore;
    private Integer scoreParHole1;
    private Integer scoreParHole2;
    private Integer scoreParHole3;
    private Integer scoreParHole4;
    private Integer scoreParHole5;
    private Integer scoreParHole6;
    private Integer scoreParHole7;
    private Integer scoreParHole8;
    private Integer scoreParHole9;
    private Integer scoreParHole10;
    private Integer scoreParHole11;
    private Integer scoreParHole12;
    private Integer scoreParHole13;
    private Integer scoreParHole14;
    private Integer scoreParHole15;
    private Integer scoreParHole16;
    private Integer scoreParHole17;
    private Integer scoreParHole18;
    private Integer totalScorePar;
}
