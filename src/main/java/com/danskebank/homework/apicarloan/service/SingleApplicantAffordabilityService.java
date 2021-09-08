package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;

import java.math.BigDecimal;

public class SingleApplicantAffordabilityService extends ApplicantAffordabilityService {

  private Applicant applicant;

  public SingleApplicantAffordabilityService(Applicant applicant) {
    super.applicant = applicant;
  }

  @Override
  public BigDecimal getMaritalCoefficient() {
    return BigDecimal.valueOf(1);
  }
}
