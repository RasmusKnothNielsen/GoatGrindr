package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Dislike;
import edu.kea.group.goatsite.repository.DislikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class DislikeApiController {

    @Autowired
    private DislikeRepository dislikeRepository;

    @PostMapping("/dislike")
    public Dislike newDislike(@Valid @RequestBody Dislike dislike){
        return dislikeRepository.save(dislike);
    }

}
