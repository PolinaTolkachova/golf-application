package com.golf.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
@Entity
public class Par {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer parHole01;
    private Integer parHole02;
    private Integer parHole03;
    private Integer parHole04;
    private Integer parHole05;
    private Integer parHole06;
    private Integer parHole07;
    private Integer parHole08;
    private Integer parHole09;
    private Integer parHole10;
    private Integer parHole11;
    private Integer parHole12;
    private Integer parHole13;
    private Integer parHole14;
    private Integer parHole15;
    private Integer parHole16;
    private Integer parHole17;
    private Integer parHole18;

}
