package com.golf.app.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Hole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer holeNumber;
    private Integer strokeIndex;
    private Integer par;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tee> tees;
}
