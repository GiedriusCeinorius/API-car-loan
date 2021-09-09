package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;

public abstract class AffordabilityService {

  public static final BigDecimal CHILDREN_COEFFICIENT = BigDecimal.valueOf(400);
  public static final BigDecimal ADULT_COEFFICIENT = BigDecimal.valueOf(600);

  protected Applicant applicant;
  protected AffordabilityRepository affordabilityRepository;

  AffordabilityService(Applicant applicant, AffordabilityRepository affordabilityRepository) {
    this.applicant = applicant;
    this.affordabilityRepository = affordabilityRepository;
  }

  public abstract BigDecimal getMaritalCoefficient();

  public Long getAffordability() {
    BigDecimal affordability = calculateAffordability();
    return saveAffordability(new Affordability(affordability, applicant));
  }

  private BigDecimal calculateAffordability() {
    return applicant.getIncomeAfterTax()
            .subtract(BigDecimal.valueOf(applicant.getChildrenNo()).multiply(CHILDREN_COEFFICIENT))
            .subtract(BigDecimal.valueOf(applicant.getAdultNo()).multiply(ADULT_COEFFICIENT))
            .multiply(getMaritalCoefficient());
  }

  public Long saveAffordability(Affordability affordability) {
    Affordability createdAffordability = affordabilityRepository.save(affordability);
    return createdAffordability.getId();
  }

}
