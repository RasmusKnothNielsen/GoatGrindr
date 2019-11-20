package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Like;
import edu.kea.group.goatsite.model.Match;
import edu.kea.group.goatsite.repository.LikeRepository;
import edu.kea.group.goatsite.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class LikeApiController {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private MatchRepository matchRepository;

    @PostMapping("/like")
    public Like newLike(@Valid @RequestBody Like like) {
        return likeRepository.save(like);
    }

    @PostMapping("/checkmatch")
    public boolean checkMatch(@Valid @RequestBody Like like) {
        Long liker = like.getGoatLiker().getId();
        Long liked = like.getGoatLiked().getId();
        Iterable<Like> matches = likeRepository.findMatch(liker,liked);

        if(matches.iterator().hasNext()) {

            //System.out.println("Match found!");
            Match match = new Match();
            match.setGoat1Id(like.getGoatLiked());
            match.setGoat2Id(like.getGoatLiker());
            matchRepository.save(match);
            return true;

        } else {

            //System.out.println("No match.");
            return false;

        }
    }

}
