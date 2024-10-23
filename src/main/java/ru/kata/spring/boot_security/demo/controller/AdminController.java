package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.security.PersonDetails;
import ru.kata.spring.boot_security.demo.service.PersonService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;
    private final RoleService roleService;

    @Autowired
    public AdminController(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String admin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("admin", personDetails);
        return "adminController/admin";
    }

    @GetMapping("/listOfUsers")
    public String listOfUsers(Model model) {
        List<Person> list = personService.getAll();
        model.addAttribute("list", list);
        return "adminController/list_of_users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        Person person = new Person();
        String roles = null;
        model.addAttribute("person", person);
        return "adminController/add_user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("person") Person person) {
        Role userRole;
        try {
            userRole = roleService.findByRoleName("ROLE_USER");
            userRole.getPersons().add(person);
            person.setRoles(new HashSet<>(Collections.singleton(userRole)));
        } catch (RoleNotFoundException e) {
            throw new RuntimeException(e);
        }
        personService.add(person);
        return "redirect:/admin/listOfUsers";
    }

    @GetMapping("/update")
    public String update(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute(personService.get(id));
        return "adminController/update_user";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        Person person = personService.get(id);
        personService.delete(person);
        return "redirect:/admin/listOfUsers";
    }
}
