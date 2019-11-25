package edu.kea.group.goatsite.service;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.model.Match;
import edu.kea.group.goatsite.repository.GoatRepository;
import edu.kea.group.goatsite.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    GoatRepository goatRepository;

    @Autowired
    MatchRepository matchRepository;

    Iterable<Match> findMatchByGoat1IdOrGoat2Id(Goat goat) {
        Iterable<Match> foundMatches = matchRepository.findMatchByGoat1(goat);
        return foundMatches;
    }

}
