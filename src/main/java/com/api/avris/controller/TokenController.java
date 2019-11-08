package com.api.avris.controller;

import com.api.avris.exception.ResourceNotFoundException;
import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Users;
import com.api.avris.repositories.RoleRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class TokenController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/signin")
    public ApiResponse<Users> getToken(@RequestParam("email") final String email){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", usersService.login(email));
    }

    @PostMapping("/verifi")
    public ApiResponse<Users> getCode(@RequestParam("code") final String code){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", usersRepository.postCode(code));
    }

    @PostMapping("/signup/{roleId}")
    public ApiResponse<Object> createComment(@PathVariable (value = "roleId") Long roleId,
                                             @Valid @RequestBody Users users) {
        return roleRepository.findById(roleId).map(role -> {
            users.setRole(role);
            return new ApiResponse<>(HttpStatus.OK.value(), "success", usersService.save(users));
        }).orElseThrow(() -> new ResourceNotFoundException("roleId " + roleId + " not found"));
    }



}

