package argmus.restaurantwebapp.service.impl;

import argmus.restaurantwebapp.model.Role;
import argmus.restaurantwebapp.repository.RoleRepository;
import argmus.restaurantwebapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> initRoles() {
        List<Role> existingRoles = this.roleRepository.findAll();
        if (existingRoles.isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
            roles.add(new Role("ROLE_EMPLOYEE"));
            return this.roleRepository.saveAll(roles);
        }
        return existingRoles;
    }
}
