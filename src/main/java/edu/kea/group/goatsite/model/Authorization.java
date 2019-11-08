package edu.kea.group.goatsite.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "authorization")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE) //TODO - Virker dette?
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goat goat;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
}
