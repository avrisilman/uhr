package com.api.avris.repositories;

import com.api.avris.jpa.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    @Query(value = "SELECT u FROM Users u where u.handphone = ?1")
    Optional<Users> login(String handphone);

    Optional<Users> findByToken(String token);

}

