package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Person;

public interface PersonService {
    void add(Person person);
    Person get(Long id);
    void update(Person person);
    void delete(Person person);
}
