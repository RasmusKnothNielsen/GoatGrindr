package edu.kea.group.goatsite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
@Entity
@Table(name = "goats")
public class Goat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE) //TODO - Virker dette?
    private Long id;

    private String name;

    @NotNull
    @Past
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Length(max = 120)
    private String shortDescription;

    @Length(max = 10000)
    private String longDescription;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotNull
    private boolean enabled;

}
