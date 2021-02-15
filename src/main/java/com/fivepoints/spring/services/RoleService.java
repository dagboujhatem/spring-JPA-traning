package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.Role;
import com.fivepoints.spring.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role saveNewRole(Role role)
    {
        return this.roleRepository.save(role);
    }

    public List<Role> getAllRoles()
    {
        return this.roleRepository.findAll();
    }

    public Role findRoleByID(long id)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        // Return statement if user exist or null
        return roleData.orElse(null);

    }

    public String updateRoleByID(long id, Role role)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            Role existingRole = roleData.orElse(null);
            existingRole.setName(role.getName());
            // save existingUser in the database
            this.roleRepository.save(existingRole);
            // return statement
            return "Role updated successfully!";
        } else {
            return "Role not found";
        }
    }

    public String deleteRoleById(long id)
    {
        Optional<Role> roleData = this.roleRepository.findById(id);
        if (roleData.isPresent()) {
            this.roleRepository.deleteById(id);
            return "Role deleted successfully!";
        } else {
            return "Role not found";
        }
    }
}
