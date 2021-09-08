package com.danskebank.homework.apicarloan.repository;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
