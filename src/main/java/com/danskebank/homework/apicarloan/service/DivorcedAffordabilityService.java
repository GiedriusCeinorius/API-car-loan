package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;

public class DivorcedAffordabilityService extends AffordabilityService {

  public DivorcedAffordabilityService(Applicant applicant,
                                      AffordabilityRepository affordabilityRepository) {
    super(applicant, affordabilityRepository);
  }

  @Override
  public BigDecimal getMaritalCoefficient() {
    BigDecimal maritalCoefficient = null;
    Integer adultNo = applicant.getAdultNo();
    if (adultNo == 1) {
      maritalCoefficient = BigDecimal.valueOf(1);
    }
    if (adultNo == 2) {
      maritalCoefficient = BigDecimal.valueOf(0.8);
    }
    return maritalCoefficient;
  }
}
