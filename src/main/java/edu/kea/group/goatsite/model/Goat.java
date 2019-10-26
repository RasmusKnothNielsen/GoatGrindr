package edu.kea.dahl.goatsite.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "goats")
public class Goat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Date dob;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Length(max = 120)
    private String shortDescription;

    @Length(max = 10000)
    private String longDescription;

    //@Column(nullable = false)
    private String username;
    private String password;

}
