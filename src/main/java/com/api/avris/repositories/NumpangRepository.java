package com.api.avris.repositories;

import com.api.avris.jpa.Numpang;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface NumpangRepository extends CrudRepository<Numpang, Long> {

    @Query(value = "SELECT us.*, n.* FROM users us INNER JOIN numpang n ON n.users_id=us.users_id", nativeQuery = true)
    List<Numpang> findNumpangById();

}
