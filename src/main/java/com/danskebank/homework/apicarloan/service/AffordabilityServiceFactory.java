package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.enums.MaritalStatus;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;
import org.springframework.stereotype.Service;

@Service
public class AffordabilityServiceFactory {

  private final AffordabilityRepository affordabilityRepository;

  public AffordabilityServiceFactory(AffordabilityRepository affordabilityRepository) {
    this.affordabilityRepository = affordabilityRepository;
  }


  public AffordabilityService getAffordabilityService(Applicant applicant) {
    MaritalStatus applicantMaritalStatus = applicant.getMaritalStatus();
    AffordabilityService affordabilityService = null;
    if (applicantMaritalStatus.equals(MaritalStatus.MARRIED)) {
      affordabilityService = new MarriedAffordabilityService(applicant, affordabilityRepository);
    }
    if (applicantMaritalStatus.equals(MaritalStatus.SINGLE)) {
      affordabilityService = new SingleAffordabilityService(applicant, affordabilityRepository);
    }
    if (applicantMaritalStatus.equals(MaritalStatus.DIVORCED)) {
      affordabilityService = new DivorcedAffordabilityService(applicant, affordabilityRepository);
    }
    return affordabilityService;
  }
}
