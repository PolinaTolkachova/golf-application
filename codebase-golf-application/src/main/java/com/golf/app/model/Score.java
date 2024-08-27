package com.golf.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stroke;
    private Integer penalty;
    private Integer grossScore;
    private Integer netScore;
    private Integer netScorePar;
    private Integer stableford;
    private Integer scoreSubtractPar;
    private Integer scoreSubtractParHcp;
}
