package com.api.avris.repositories;

import com.api.avris.jpa.Hobi;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobiRepository extends CrudRepository<Hobi, Long> {
    @Query(value = "SELECT h.* FROM hobi h WHERE h.sub_id=:sub_id", nativeQuery = true)
    List<Hobi> findHobiById(@Param("sub_id") long sub_id);

    @Query(value = "SELECT h.* FROM hobi h INNER JOIN sub_menu sb ON h.sub_id=sb.sub_id WHERE h.users_id=:users_id AND sb.menu_id=:menu_id", nativeQuery = true)
    List<Hobi> findHobiEditById(@Param("users_id") long users_id, @Param("menu_id") long menu_id);
}
