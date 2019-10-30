package com.api.avris.repositories;

import com.api.avris.jpa.SubMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMenuRepository extends CrudRepository<SubMenu, Long> {
    @Query(value = "SELECT \n" +
            "mn.menu_id,\n" +
            "mn.name_menu,\n" +
            "sb.sub_id,\n" +
            "sb.name_sub_menu,\n" +
            "sb.url_image_sub\n" +
            "FROM sub_menu sb \n" +
            "JOIN menus mn \n" +
            "on mn.menu_id=sb.menu_id \n" +
            "where sb.menu_id=:menu_id", nativeQuery = true)
    List<SubMenu> findMenuById(@Param("menu_id") long menu_id);



}


