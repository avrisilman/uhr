package com.api.avris.repositories;

import com.api.avris.jpa.ChildMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends CrudRepository<ChildMenu, Long> {

    @Query(value = "SELECT cm.* FROM users us INNER JOIN role r ON us.role_id=r.role_id INNER JOIN role_menu rm ON rm.role_id=r.role_id INNER JOIN menus m ON m.menu_id=rm.menu_id INNER JOIN sub_menu sm ON sm.menu_id=m.menu_id INNER JOIN child_menu cm ON cm.sub_id = sm.sub_id WHERE us.users_id=4 AND sm.sub_id=:sub_id", nativeQuery = true)
    List<ChildMenu> findChildMenuById(@Param("sub_id") long sub_id);

}

