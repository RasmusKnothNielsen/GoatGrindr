package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Like;
import edu.kea.group.goatsite.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class LikeApiController {

    @Autowired
    private LikeRepository likeRepository;

    @PostMapping("/like")
    public Like newLike(@Valid @RequestBody Like like){
        return likeRepository.save(like);
    }

}
