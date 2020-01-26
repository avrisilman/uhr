package com.api.avris.repositories;

import com.api.avris.jpa.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    @Query(value = "SELECT m.* FROM menus m INNER JOIN role_menu rm ON m.menu_id=rm.menu_id WHERE m.menu_id NOT IN(1,3,6,8) AND rm.role_id=:role_id", nativeQuery = true)
    List<Menu> findMenuById(@Param("role_id") long role_id);
}

