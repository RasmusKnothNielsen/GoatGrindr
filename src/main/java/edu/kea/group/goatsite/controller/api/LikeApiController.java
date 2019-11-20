package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Like;
import edu.kea.group.goatsite.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class LikeApiController {

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping("/checkmatch")
    public boolean checkMatch(@Valid @RequestBody Like like) {
        Long liker = like.getGoatLiker().getId();
        Long liked = like.getGoatLiked().getId();
        Iterable<Like> match = likeRepository.findMatch(liker,liked);
        if(match.iterator().hasNext()) {
            System.out.println("Match found!");
            return true;
        } else {
            System.out.println("No match.");
            return false;
        }
    }

    @PostMapping("/like")
    public Like newLike(@Valid @RequestBody Like like) {
        return likeRepository.save(like);
    }

}
