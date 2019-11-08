package edu.kea.group.goatsite.controller.view;

import edu.kea.group.goatsite.model.Goat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainViewController {

    @GetMapping(value = "/test")
    public String tryTheTestPath(Model model, @RequestParam(value = "name") String visitorName) {
        model.addAttribute("name", visitorName);

        List<String> qualities = Arrays.asList("Can dance", "Can run", "Can sing");
        model.addAttribute("qualities", qualities);

        return "test.html";
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
