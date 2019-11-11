package com.api.avris.repositories;

import com.api.avris.jpa.ChildMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends CrudRepository<ChildMenu, Long> {

    @Query(value = "SELECT cm.* FROM child_menu cm WHERE cm.users_id=:users_id AND cm.sub_id=:sub_id", nativeQuery = true)
    List<ChildMenu> findChildMenuById(@Param("users_id") long users_id, @Param("sub_id") long sub_id);

}

