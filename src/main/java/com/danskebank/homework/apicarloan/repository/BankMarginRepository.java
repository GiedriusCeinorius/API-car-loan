package com.danskebank.homework.apicarloan.repository;

import com.danskebank.homework.apicarloan.domain.BankMargin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankMarginRepository extends CrudRepository<BankMargin, Long> {
}
