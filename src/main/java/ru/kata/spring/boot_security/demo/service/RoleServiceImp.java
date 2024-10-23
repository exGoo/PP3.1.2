package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

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

    @Override
    public Role findByRoleName(String name) throws RoleNotFoundException {
        Optional<Role> role = roleRepository.findByRoleName(name);
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Role not found");
        }
        return role.get();
    }
}
