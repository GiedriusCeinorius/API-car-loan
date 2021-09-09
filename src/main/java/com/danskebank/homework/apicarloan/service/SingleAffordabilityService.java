package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;

public class SingleAffordabilityService extends AffordabilityService {

  public SingleAffordabilityService(Applicant applicant, AffordabilityRepository affordabilityRepository) {
    super(applicant, affordabilityRepository);
  }

  @Override
  public BigDecimal getMaritalCoefficient() {
    return BigDecimal.valueOf(1);
  }
}
