package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Role;
import com.api.avris.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value = "/api/menu/{roleId}")
    public ApiResponse<Role> getMenuId(@PathVariable long roleId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", roleRepository.findById(roleId));
    }

}

