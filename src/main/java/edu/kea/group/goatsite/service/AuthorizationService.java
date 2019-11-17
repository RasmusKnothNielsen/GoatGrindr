package edu.kea.group.goatsite.service;

import edu.kea.group.goatsite.model.Authorization;
import edu.kea.group.goatsite.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    AuthorizationRepository authorizationRepository;

    public void add(Authorization authorization) {
        authorizationRepository.save(authorization);
    }

}
