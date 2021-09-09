package com.danskebank.homework.apicarloan.repository;

import com.danskebank.homework.apicarloan.domain.Affordability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffordabilityRepository extends CrudRepository<Affordability, Long> {
}
