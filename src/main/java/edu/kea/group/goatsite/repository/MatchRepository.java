package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Match;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM matches WHERE goat1_id = ? OR goat2_id = ?", nativeQuery = true)
    void deleteAllByGoatId(Long id1, Long id2); //TODO - Avoid repetition.

    Iterable<Match> findMatchByGoat1(Goat goat);
}
