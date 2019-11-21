package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Authorization;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long> {

    // SELECT specific goat by id and change role from user to admin
    @Query(value = "INSERT INTO authorization (goat_id, role)\n" +
            "VALUES (:goat_id, 'ROLE_ADMIN')", nativeQuery = true)
    Authorization setRoleToAdmin(@Param("goat_id") Long goat_id);

}
