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
    @ManyToOne(cascade = CascadeType.ALL)
    private Goat goat1Id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Goat goat2Id;

}
