package com.golf.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id")
    private Competition competition;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RoundScore> roundscores;
    private int roundNumber;
    private LocalDate date;
    @ManyToMany
    private List<Player> roundplayers;
    private String name;
}
