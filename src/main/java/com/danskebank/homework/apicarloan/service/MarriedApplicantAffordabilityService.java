package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public class MarriedApplicantAffordabilityService extends ApplicantAffordabilityService {

//  private Applicant applicant;

  public MarriedApplicantAffordabilityService(Applicant applicant) {
    super.applicant = applicant;
  }

  @Override
  public BigDecimal getMaritalCoefficient() {
    return BigDecimal.valueOf(0.7);
  }
}
