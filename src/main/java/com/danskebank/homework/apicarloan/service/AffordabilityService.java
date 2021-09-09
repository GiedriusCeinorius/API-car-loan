package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;

public abstract class AffordabilityService {

  protected Applicant applicant;
  protected AffordabilityRepository affordabilityRepository;

  AffordabilityService(Applicant applicant, AffordabilityRepository affordabilityRepository) {
    this.applicant = applicant;
    this.affordabilityRepository = affordabilityRepository;
  }

  public abstract BigDecimal getMaritalCoefficient();

  public Long createAffordability() {
    BigDecimal maritalCoefficient = getMaritalCoefficient();
    System.out.println(applicant.getMaritalStatus() + "" + applicant.getAdultNo());
    BigDecimal subtract = applicant.getIncomeAfterTax().subtract(BigDecimal.valueOf(applicant.getChildrenNo() * 400)).subtract(BigDecimal.valueOf(applicant.getAdultNo() * 600));
    return saveAffordability(new Affordability(subtract, applicant));
  }

  public Long saveAffordability(Affordability affordability) {
    Affordability createdAffordability = affordabilityRepository.save(affordability);
    return createdAffordability.getId();
  }

}
