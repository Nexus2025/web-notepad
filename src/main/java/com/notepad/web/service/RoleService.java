package com.notepad.web.service;

import com.notepad.web.entity.Role;
import com.notepad.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getByName(String name) {
        return roleRepository.findAll().stream().filter(r -> r.getName().equals(name)).findFirst().get();
    }
}
