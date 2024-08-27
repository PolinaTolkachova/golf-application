package com.golf.app.model;

import com.golf.app.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    private String email;
    private Double hcp;
    private Long photoId;

    @ManyToOne(fetch = FetchType.EAGER) // Adjust fetch type as needed (LAZY or EAGER)
    @JoinColumn(name = "round_id") // Specify the foreign key column name
    private Round round;
}
