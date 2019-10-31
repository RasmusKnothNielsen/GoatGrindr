package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Goat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoatRepository extends CrudRepository<Goat, Long> {
}
