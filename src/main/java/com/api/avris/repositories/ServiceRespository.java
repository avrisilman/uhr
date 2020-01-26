package com.api.avris.repositories;

import com.api.avris.jpa.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRespository extends CrudRepository<Service, Long> {
}
