package com.api.avris.service;

import com.api.avris.jpa.Users;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UsersService {
    Users login(String handphone);
    Optional<User> findByToken(String token);
    Users save(Users user);
}

