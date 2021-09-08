package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.enums.MaritalStatus;
import org.springframework.stereotype.Service;

@Service
public class AffordabilityServiceFactory {

  public ApplicantAffordabilityService getAffordabilityService(Applicant applicant) {
    MaritalStatus applicantMaritalStatus = applicant.getMaritalStatus();
    ApplicantAffordabilityService applicantAffordabilityService = null;
    if (applicantMaritalStatus.equals(MaritalStatus.MARRIED)) {
      applicantAffordabilityService = new MarriedApplicantAffordabilityService(applicant);
    }
    if (applicantMaritalStatus.equals(MaritalStatus.SINGLE)) {
      applicantAffordabilityService = new SingleApplicantAffordabilityService(applicant);
    }
    if (applicantMaritalStatus.equals(MaritalStatus.DIVORCED)) {
      applicantAffordabilityService = new DivorcedApplicantAffordabilityService(applicant);
    }
    return applicantAffordabilityService;
  }
}
