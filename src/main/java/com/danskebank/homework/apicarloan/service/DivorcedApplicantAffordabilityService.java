package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class DivorcedApplicantAffordabilityService extends ApplicantAffordabilityService {



  public DivorcedApplicantAffordabilityService(Applicant applicant) {
    super.applicant = applicant;
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
