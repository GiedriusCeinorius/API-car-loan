package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.enums.MaritalStatus;

import java.math.BigDecimal;

public abstract class ApplicantAffordabilityService {

  protected Applicant applicant;

  public abstract BigDecimal getMaritalCoefficient();

  public Affordability getAffordability() {
    BigDecimal maritalCoefficient = getMaritalCoefficient();
    System.out.println(applicant.getMaritalStatus() + "" + applicant.getAdultNo());
    BigDecimal subtract = applicant.getIncomeAfterTax().subtract(BigDecimal.valueOf(applicant.getChildrenNo() * 400)).subtract(BigDecimal.valueOf(applicant.getAdultNo() * 600));
    return new Affordability(subtract, applicant);
  }

}
