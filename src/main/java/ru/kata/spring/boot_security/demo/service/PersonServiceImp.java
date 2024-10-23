package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Person;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImp(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void add(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @Override
    public Person get(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void update(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void delete(Person person) {
        for (Role role : person.getRoles()) {
            role.getPersons().remove(person);
        }
        person.setRoles(null);
        personRepository.delete(person);
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
