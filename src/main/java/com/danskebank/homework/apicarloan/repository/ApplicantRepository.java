package com.danskebank.homework.apicarloan.repository;

import com.danskebank.homework.apicarloan.domain.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
}
