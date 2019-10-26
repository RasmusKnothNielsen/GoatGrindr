package edu.kea.dahl.goatsite.model;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long goat_liker;
    private Long goat_liked;

}
