package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Authorization;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM authorizations WHERE goat_id = ?", nativeQuery = true)
    void deleteAllByGoatId(Long id);

}
