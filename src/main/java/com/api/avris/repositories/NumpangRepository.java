package com.api.avris.repositories;

import com.api.avris.jpa.Numpang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NumpangRepository extends CrudRepository<Numpang, Long> {

    @Query(value = "SELECT m.* FROM numpang m WHERE m.users_id=:users_id", nativeQuery = true)
    List<Numpang> findNumpangEditById(@Param("users_id") long users_id);

}
