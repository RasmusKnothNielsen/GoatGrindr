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

    // TODO: Add @ManyToOne annotation if possible? And necessary.
    @Id
    private Long goat_Id;

    private String role;
}
