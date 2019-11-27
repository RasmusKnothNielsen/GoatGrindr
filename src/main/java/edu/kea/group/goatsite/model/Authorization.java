package edu.kea.group.goatsite.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "authorizations")
public class Authorization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    //TODO: Add @ManyToOne annotation if possible? And necessary.
    private Long goatId;
}
