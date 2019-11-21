package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Authorization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {
}
