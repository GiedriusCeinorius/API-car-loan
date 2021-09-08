package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.repository.ApplicantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ApplicantService {

  private final ApplicantRepository applicantRepository;

  public ApplicantService(ApplicantRepository applicantRepository) {
    this.applicantRepository = applicantRepository;
  }

  public Long saveApplicantRequest(Applicant applicant, Affordability affordability) {
    applicant.setAffordability(affordability);
    Applicant savedApplicant = applicantRepository.save(applicant);
    return savedApplicant.getAffordability().getId();

  }
}
