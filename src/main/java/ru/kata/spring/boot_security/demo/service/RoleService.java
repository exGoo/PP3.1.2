package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

public interface RoleService {
    void add(Role role);
    Role get(Integer id);
    void update(Role role);
    void delete(Role role);
    Role findByRoleName(String name) throws RoleNotFoundException;
}
