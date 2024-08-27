package com.golf.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StrokeIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer strokeIndexHole01;
    private Integer strokeIndexHole02;
    private Integer strokeIndexHole03;
    private Integer strokeIndexHole04;
    private Integer strokeIndexHole05;
    private Integer strokeIndexHole06;
    private Integer strokeIndexHole07;
    private Integer strokeIndexHole08;
    private Integer strokeIndexHole09;
    private Integer strokeIndexHole10;
    private Integer strokeIndexHole11;
    private Integer strokeIndexHole12;
    private Integer strokeIndexHole13;
    private Integer strokeIndexHole14;
    private Integer strokeIndexHole15;
    private Integer strokeIndexHole16;
    private Integer strokeIndexHole17;
    private Integer strokeIndexHole18;

}
