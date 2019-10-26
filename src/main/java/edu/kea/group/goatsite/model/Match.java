package edu.kea.group.goatsite.model;

import javax.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long goat1_id;
    private Long goat2_id;

}
