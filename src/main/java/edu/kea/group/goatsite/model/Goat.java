package edu.kea.group.goatsite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "goats")
public class Goat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Past
    //TODO: Add exceptionhandling if user writes a date that is after the current date or if user is above 150 years?
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

    @NotNull
    private boolean enabled;

    @OneToMany(mappedBy = "goatId", fetch = FetchType.LAZY)
    private List<Authorization> authorizations;

    @OneToMany(mappedBy = "goatLiker", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Like> likes;

    @OneToMany(mappedBy = "goatDisliker", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Dislike> dislikes;

}
