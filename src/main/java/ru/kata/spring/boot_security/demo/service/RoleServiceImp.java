package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role get(Integer id) {
        return roleRepository.getById(id);
    }

    @Override
    @Transactional
    public void update(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
