package com.golf.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    @ManyToOne
    private Course course;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("roundNumber DESC")
    private List<Round> rounds;

    @OneToMany
    private List<Judge> judges;

    @ManyToMany
    @OrderBy("surname ASC")
    private List<Player> players;

    @OneToMany
    private List<Team> teams;
}
