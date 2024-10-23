package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.security.PersonDetails;
import ru.kata.spring.boot_security.demo.service.PersonServiceImp;

@Controller
@RequestMapping("/user")
public class UserController {

    private final PersonServiceImp personServiceImp;

    @Autowired
    public UserController(PersonServiceImp personServiceImp) {
        this.personServiceImp = personServiceImp;
    }

    @GetMapping()
    public String user(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute(personServiceImp.get(id));
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
            model.addAttribute("person", personDetails.getPerson());
        }
        return "userController/user";
    }

}
