package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
public class GoatApiController {

    @Autowired
    private GoatRepository goatRepository;

    @GetMapping("/goats")
    public Iterable<Goat> getGoats() {
        System.out.println("Test");
        return goatRepository.findAll();
    }

    @PostMapping("/goats")
    public Goat createGoat(@Valid @RequestBody Goat goat){
        return goatRepository.save(goat);
    }

}
