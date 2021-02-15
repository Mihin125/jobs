package com.job.service;

import com.job.Authentication.UserRole;
import com.job.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRole findById(long id){
        return userRoleRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public UserRole findByName(String name){
        return userRoleRepository.findUserRoleByName(name);
    }

    public void save(com.demo.dto.RoleDto roleDto){
        UserRole userRole = new UserRole();
        userRole.setName(roleDto.getRole());
        userRoleRepository.save(userRole);
    }
}
