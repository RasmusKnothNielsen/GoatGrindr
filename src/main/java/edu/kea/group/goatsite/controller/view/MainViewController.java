package edu.kea.group.goatsite.controller.view;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainViewController {

    @Autowired
    private GoatRepository goatRepository;

    @RequestMapping("/js/MainPage.js")
    public String main(Model model, Principal principal) {
        Goat user = goatRepository.findByUsername(principal.getName());
        Long userId = user.getId();
        Iterable<Goat> candidates = goatRepository.findCandidates(userId, userId, userId); //TODO - Find a way to avoid this in SQL Statement

        model.addAttribute("user", user);
        model.addAttribute("candidates", candidates);
        return "../static/js/MainPage.js";
    }

    @GetMapping(value = "/settings.html")
    public String goToSettings(){
        return "settings.html";
    }

    //TODO: Get the information from the goat object and update it to the database.
    @PostMapping("/changeinformation")
    public String addUser(@ModelAttribute Goat goat) {
        //userService.addUser(goat);
        return "profile.html";
    }

}
