package edu.kea.group.goatsite.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goat goatLiker;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Goat goatLiked;

}
