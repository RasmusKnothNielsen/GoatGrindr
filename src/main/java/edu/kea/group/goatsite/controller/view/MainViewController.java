package edu.kea.group.goatsite.controller.view;

import edu.kea.group.goatsite.model.Gender;
import edu.kea.group.goatsite.model.Goat;
import edu.kea.group.goatsite.repository.GoatRepository;
import edu.kea.group.goatsite.service.GoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String main(Model model) {
        Iterable<Goat> candidates = goatRepository.findCandidates();
        model.addAttribute("candidates", candidates);
        return "../static/js/MainPage.js";
    }

    @GetMapping(value = "/settings.html")
    public String goToSettings() {
        return "settings.html";
    }

    //TODO: Get the information from the goat object and update it to the database.
    @PostMapping("/changeinformation")
    public String profile(@ModelAttribute Goat goat) {
        //userService.addUser(goat);
        return "profile.html";
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

    /* TODO: Get a count on the amount of goats in the goat table - increment the count number and add a row
    *   in the role table  */

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

    @RequestMapping(value = "/adminpanel.html", method = RequestMethod.GET)
    public String adminPanel(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {

        // if the user authorities
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return "adminpanel.html";
            }
            if (authority.getAuthority().equals("ROLE_USER")) {
                return "Unauthorized access"; // TODO Lav en unauthorized acces page, som vi kan vise.
            }
        }
        return null;
    }

    @RequestMapping(value = "/userpanel.html", method = RequestMethod.GET)
    public String userPanel() {
        return "userpanel.html";
    }

    @RequestMapping(value = "/profile.html", method = RequestMethod.GET)
    public String profile() {
        return "profile.html";
    }

    // TODO add postmapping that changes the goats profile information


} // closing bracket for class