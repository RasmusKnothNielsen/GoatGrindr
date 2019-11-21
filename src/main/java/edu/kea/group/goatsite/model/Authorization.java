package edu.kea.group.goatsite.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "authorization")
public class Authorization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: Make this enum again and test if it works
    @Enumerated(EnumType.STRING)
    private Role role;

    // TODO: Add @ManyToOne annotation if possible? And necessary.
    @Column(name = "goat_id")
    private Long goatid;
}
