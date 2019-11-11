package com.api.avris.repositories;

import com.api.avris.jpa.Market;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends CrudRepository<Market, Long> {
}
