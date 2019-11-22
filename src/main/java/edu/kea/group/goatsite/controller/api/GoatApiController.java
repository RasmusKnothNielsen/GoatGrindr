package edu.kea.group.goatsite.controller.api;

import edu.kea.group.goatsite.model.Gender;
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
        return goatRepository.findAll();
    }

    @GetMapping("/goats/{id}")
    public Goat getGoatById(@PathVariable("id") Long id) {
        return goatRepository.findById(id).orElse(null);
    }

    @GetMapping("/goats/findbyname")
    public Iterable<Goat> getGoatsByName(@RequestParam(value = "name") String name) {
        return goatRepository.findAllByName(name);
    }

    @GetMapping("/goats/findbygender")
    public Iterable<Goat> getGoatsByGender(@RequestParam(value = "gender") String genderString) {
        try {
            return goatRepository.findAllByGender(Gender.valueOf(genderString));
        } catch (IllegalArgumentException | NullPointerException e) {
            //Invalid gender or null
            return null;
        }
    }

    @PostMapping("/goats")
    public Goat createGoat(@Valid @RequestBody Goat goat){
        return goatRepository.save(goat);
    }

}
