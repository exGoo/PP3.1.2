package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.PersonServiceImp;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HelloController {

    private final PersonServiceImp personServiceImp;
    private final RoleServiceImp roleServiceImp;

    @Autowired
    public HelloController(PersonServiceImp personServiceImp, RoleServiceImp roleServiceImp) {
        this.personServiceImp = personServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/init")
    public String init() {
        Person person = new Person("Vlad", "Guliaev", "admin", "admin");
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        admin.setPersons(new HashSet<>(Set.of(person)));
        user.setPersons(new HashSet<>(Set.of(person)));
        person.setRoles(new HashSet<>(Set.of(admin, user)));
        personServiceImp.add(person);

        return "redirect:/auth";
    }
}
