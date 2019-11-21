package edu.kea.group.goatsite.controller.view;

import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import edu.kea.group.goatsite.service.GoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
public class MainViewController {

    @Autowired
    private GoatRepository goatRepository;

    @Autowired
    private GoatService goatService;


    @RequestMapping("/js/MainPage.js")
    public String main(Model model, Principal principal) {
        Goat user = goatRepository.findByUsername(principal.getName());
        Long userId = user.getId();
        Iterable<Goat> candidates = goatRepository.findCandidates(userId, userId, userId); //TODO - Find a way to avoid this in SQL Statement

        model.addAttribute("user", user);
        model.addAttribute("candidates", candidates);
        return "../static/js/MainPage.js";
    }

    // Get the index file if the user is logged in, else get the login file
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        // if the user or the user authorities equals null return login file
        if (user == null || user.getAuthorities() == null) {
            return "login";
        }

        // if the user authorities
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_USER")) {
                return "index";
            }
        }
        return "login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/adduser")
    public String addUserSite() {
        return "adduser";
    }

    // add a new Goat to the database
    @PostMapping("/adduser")
    public String addOneGoat(Goat goat) {
        goatService.addGoat(goat);
        return "redirect:/login";
    }

    @RequestMapping(value = "/matches.html", method = RequestMethod.GET)
    public String matches(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        return "matches.html";
    }

    // Getmapping to show the admin panel
    @RequestMapping(value = "/adminpanel", method = RequestMethod.GET)
    public String adminPanel(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        // if the user authorities
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "adminpanel";
            }
            if (authority.getAuthority().equals("ROLE_USER")) {
                return "Unauthorized access"; // TODO Lav en unauthorized acces page, som vi kan vise.
            }
        }
        return null;
    }

    // Getmapping to get a list of all goats in the admin panel
    @GetMapping("/listofgoats")
    public String getListOfGoats(Model model) {
        Iterable<Goat> getAllGoats = goatRepository.findAll();
        model.addAttribute("getGoats", getAllGoats);
        return "listofgoats";
    }

    @RequestMapping(value = "/userpanel.html", method = RequestMethod.GET)
    public String userPanel() {
        return "userpanel.html";
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public String profile(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        model.addAttribute("goat", goatRepository.findByUsername(user.getUsername()));
        return "profile.html";
    }

    @PostMapping("/listofgoats/{id}")
    public String deleteGoatById(@PathVariable Long id) {
        goatRepository.deleteById(id);
        return "redirect:/listofgoats";
    }

    // Get the updated information from our profile page, update the Goat object and save it to the database.
    @PostMapping("/changeinformation")
    public String profile(@ModelAttribute Goat goat) {
        Goat newGoat = goatRepository.findByUsername(goat.getUsername());
        newGoat.setName(goat.getName());
        newGoat.setShortDescription(goat.getShortDescription());
        newGoat.setLongDescription(goat.getLongDescription());
        newGoat.setGender(goat.getGender());
        goatRepository.save(newGoat);
        return "index.html";
    }


} // closing bracket for class