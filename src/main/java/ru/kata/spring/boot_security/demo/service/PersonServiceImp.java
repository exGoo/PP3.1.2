package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public void add(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person get(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void update(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        personRepository.delete(person);
    }
}
