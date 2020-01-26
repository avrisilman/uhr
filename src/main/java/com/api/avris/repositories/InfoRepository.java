package com.api.avris.repositories;

import com.api.avris.jpa.Info;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InfoRepository extends CrudRepository<Info, Long> {
//    @Query(value = "SELECT i.* FROM info i", nativeQuery = true)
//    List<Info> findInfoById();
}