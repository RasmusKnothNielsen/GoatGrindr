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
            "JOIN authorizations on goats.id = authorizations.goat_id\n" +
            "WHERE goats.id = authorizations.goat_id\n" +
            "AND role = 'ROLE_USER';", nativeQuery = true)
    Iterable<Goat> findAllByRoleAndId();

    // save() @Query to make certain the Gender is in capital letters
    @Query(value = "INSERT INTO goats (gender) VALUES (UPPER(?))", nativeQuery = true)
    Gender addGender(Gender gender);

    @Query(value = "SELECT * FROM Goats g WHERE g.id <= 2", nativeQuery = true)
    Iterable<Goat> findTheOldTimers();

    @Query(value =
            "SELECT * FROM goats WHERE goats.id NOT IN " +
            "(SELECT goat_liked_id FROM likes WHERE goat_liker_id = ?) AND goats.id NOT IN" + //Liked
            "(SELECT goat_disliked_id FROM dislikes WHERE goat_disliker_id = ?) AND " + //Disliked
            "goats.id != ?", //Yourself
            nativeQuery = true)
    Iterable<Goat> findCandidates(Long userId1, Long userId2, Long userId3); //TODO - Avoid repeating. How?

}
