package edu.kea.group.goatsite.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
