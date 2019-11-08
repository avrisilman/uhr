package com.api.avris.repositories;

import com.api.avris.jpa.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

    @Query(value = "SELECT u FROM Users u where u.email = ?1")
    Optional<Users> login(String email);

    @Query(value = "SELECT u FROM Users u where u.code = ?1")
    Optional<Users> postCode(String code);

    @Query(value = "SELECT us.users_id,us.address,us.email,us.fullname,us.code FROM users us WHERE us.code=290252", nativeQuery = true)
    Optional<Users> findByCode(@Param("code") long code);

    Optional<Users> findByToken(String token);

}

