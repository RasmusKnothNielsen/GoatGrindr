package edu.kea.group.goatsite.model;

import javax.persistence.*;

@Entity
@Table(name = "dislikes")
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long goat_disliker;
    private Long goat_disliked;

}
