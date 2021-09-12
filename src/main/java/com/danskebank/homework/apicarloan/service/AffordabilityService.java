package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.controller.response.AffordabilityCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

  public AffordabilityCalculationResponse createAffordability() {
    BigDecimal affordability = calculateAffordability();
    Long affordabilityId = saveAffordability(new Affordability(affordability, applicant));
    return new AffordabilityCalculationResponse(affordabilityId);
  }

  private BigDecimal calculateAffordability() {
    return applicant.getIncomeAfterTax()
            .subtract(BigDecimal.valueOf(applicant.getChildrenNo()).multiply(CHILDREN_COEFFICIENT))
            .subtract(BigDecimal.valueOf(applicant.getAdultNo()).multiply(ADULT_COEFFICIENT))
            .multiply(getMaritalCoefficient())
            .setScale(2, RoundingMode.HALF_EVEN);
  }

  public Long saveAffordability(Affordability affordability) {
    Affordability createdAffordability = affordabilityRepository.save(affordability);
    return createdAffordability.getId();
  }

}
