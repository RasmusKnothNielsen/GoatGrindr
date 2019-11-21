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

//    // SELECT specific goat by id and change role from user to admin
//    @Query(value = "INSERT IGNORE INTO authorization (goat_id, role)\n" +
//            "VALUES (?, 'ROLE_ADMIN')", nativeQuery = true)
//    Authorization setRoleToAdmin(Long id);

}
