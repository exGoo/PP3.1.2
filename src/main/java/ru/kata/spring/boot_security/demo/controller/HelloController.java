package ru.kata.spring.boot_security.demo.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.model.Person;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

}
