package com.api.avris.repositories;

import com.api.avris.jpa.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventRepository  extends CrudRepository<Event, Long> {

    @Query(value = "SELECT e.* FROM event e WHERE e.users_id=:users_id", nativeQuery = true)
    List<Event> findEventBy(@Param("users_id") long users_id);

}
