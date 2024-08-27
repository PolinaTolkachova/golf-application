package com.golf.app.model;

import com.golf.app.enums.TeeColour;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Tee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TeeColour teeColour;
    private Integer length;

    public Tee(TeeColour teeColour, Integer length) {
        this.teeColour = teeColour;
        this.length = length;
    }
}
