package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Person;

import java.util.List;

public interface PersonService {
    void add(Person person);
    Person get(Long id);
    void update(Person person);
    void delete(Person person);
    List<Person> getAll();
}
