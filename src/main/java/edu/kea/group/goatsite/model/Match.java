package edu.kea.group.goatsite.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Goat goat1;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Goat goat2;

}
