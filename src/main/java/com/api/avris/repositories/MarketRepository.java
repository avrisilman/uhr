package com.api.avris.repositories;

import com.api.avris.jpa.Market;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends CrudRepository<Market, Long> {
    @Query(value = "SELECT m.* FROM market m WHERE m.sub_id=:sub_id", nativeQuery = true)
    List<Market> findMarketById(@Param("sub_id") long sub_id);

    @Query(value = "SELECT m.* FROM market m INNER JOIN sub_menu sb ON m.sub_id=sb.sub_id WHERE m.users_id=:users_id AND sb.menu_id=:menu_id", nativeQuery = true)
    List<Market> findMarketEditById(@Param("users_id") long users_id, @Param("menu_id") long menu_id);
}
