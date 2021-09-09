package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;

public class MarriedAffordabilityService extends AffordabilityService {

  public MarriedAffordabilityService(Applicant applicant, AffordabilityRepository affordabilityRepository) {
    super(applicant, affordabilityRepository);
  }

  @Override
  public BigDecimal getMaritalCoefficient() {
    return BigDecimal.valueOf(0.7);
  }
}
