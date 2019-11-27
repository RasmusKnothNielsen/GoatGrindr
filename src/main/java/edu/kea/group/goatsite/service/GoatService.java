package edu.kea.group.goatsite.service;

import edu.kea.group.goatsite.model.Authorization;
import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Role;
import edu.kea.group.goatsite.repository.AuthorizationRepository;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class GoatService {

    @Autowired
    GoatRepository goatRepository;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    PasswordEncoder passwordEncoder;


    // Add a goat to the database and hash the password
    // also add the newly created goat in the authorization table
    public void addGoat(Goat goat) {
        // TODO: Add the addGender method from GoatRepository to make sure the gender is written in uppercase to the DB
        // set the password to the given password and encode / encrypt it
        goat.setPassword(passwordEncoder.encode(goat.getPassword()));
        goatRepository.save(goat);

        // instantiate an Authorization object
        Authorization authorization = new Authorization();

        // Set authorization goat_Id to equal the Goat objects id
        authorization.setGoatId(goat.getId());
        authorization.setRole(Role.ROLE_USER);
        authorizationService.add(authorization);
    }

    // TODO: Add a method to change an admins role to user
}
