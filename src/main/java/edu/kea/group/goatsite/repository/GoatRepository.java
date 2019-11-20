package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoatRepository extends CrudRepository<Goat, Long> {

    Iterable<Goat> findAllByName(String name);
    Iterable<Goat> findAllByGender(Gender gender);
    Goat findByUsername(String username);

    // find all ROLE_USER from Authorization and all goats with the user role
    @Query(value = "SELECT *\n" +
            "FROM goats\n" +
            "JOIN authorization on goats.id = authorization.goat_id\n" +
            "WHERE goats.id = authorization.goat_id\n" +
            "AND role = 'ROLE_USER';", nativeQuery = true)
    Iterable<Goat> findAllByRoleAndId();

    // save() @Query to make certain the Gender is in capital letters
    @Query(value = "INSERT INTO goats (gender) VALUES (UPPER(?))", nativeQuery = true)
    Gender addGender(Gender gender);

    @Query(value = "SELECT * FROM Goats g WHERE g.id <= 2", nativeQuery = true)
    Iterable<Goat> findTheOldTimers();

    //TODO - Make proper query selecting goats not already liked/disliked by user
    @Query(value = "SELECT * FROM Goats g", nativeQuery = true)
    Iterable<Goat> findCandidates();

}
