package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Authorization;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizationRepository extends CrudRepository<Authorization, String> {
}
