package edu.kea.group.goatsite.repository;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    Iterable<Match> findMatchByGoat1Id(Goat goat);
}
