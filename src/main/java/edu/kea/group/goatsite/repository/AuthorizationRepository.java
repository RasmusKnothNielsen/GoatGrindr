package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Authorization;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {
}
