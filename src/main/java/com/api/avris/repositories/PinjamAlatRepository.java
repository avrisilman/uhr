package com.api.avris.repositories;

import com.api.avris.jpa.PinjamAlat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PinjamAlatRepository extends CrudRepository<PinjamAlat, Long> {

    @Query(value = "SELECT p.* FROM pinjam_alat p WHERE p.sub_id=:sub_id", nativeQuery = true)
    List<PinjamAlat> findPinjamAlatBy(@Param("sub_id") long sub_id);

    @Query(value = "SELECT p.* FROM pinjam_alat p INNER JOIN sub_menu sb ON p.sub_id=sb.sub_id WHERE p.users_id=:users_id AND sb.menu_id=:menu_id", nativeQuery = true)
    List<PinjamAlat> findPinjamAlatEditById(@Param("users_id") long users_id, @Param("menu_id") long menu_id);

}
