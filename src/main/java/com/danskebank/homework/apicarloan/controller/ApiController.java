package com.danskebank.homework.apicarloan.controller;

import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Applicant;
import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.domain.Quote;
import com.danskebank.homework.apicarloan.dto.CreditDecisionRequest;
import com.danskebank.homework.apicarloan.dto.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.service.AffordabilityServiceFactory;
import com.danskebank.homework.apicarloan.service.ApplicantService;
import com.danskebank.homework.apicarloan.service.CarLoanService;
import com.danskebank.homework.apicarloan.service.CreditDecisionService;
import com.danskebank.homework.apicarloan.service.QuoteCalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class ApiController {

  private final AffordabilityServiceFactory affordabilityServiceFactory;
  private final ApplicantService applicantService;
  private final QuoteCalculationService quoteCalculationService;
  private final CarLoanService carLoanService;
  private final CreditDecisionService creditDecisionService;

  public ApiController(AffordabilityServiceFactory affordabilityServiceFactory,
                       ApplicantService applicantService,
                       QuoteCalculationService quoteCalculationService,
                       CarLoanService carLoanService,
                       CreditDecisionService creditDecisionService) {
    this.affordabilityServiceFactory = affordabilityServiceFactory;
    this.applicantService = applicantService;
    this.quoteCalculationService = quoteCalculationService;
    this.carLoanService = carLoanService;
    this.creditDecisionService = creditDecisionService;
  }


  @PostMapping("/affordabilityCalculation")
  public ResponseEntity<Long> getAffordability(@RequestBody Applicant applicant) {
    Affordability affordability = affordabilityServiceFactory.getAffordabilityService(applicant).getAffordability();
    return new ResponseEntity<>(applicantService.saveApplicantRequest(applicant, affordability), HttpStatus.OK);
  }

  @PostMapping("/quoteCalculation")
  public ResponseEntity<QuoteCalculationResponse> getQuote(@RequestBody Car car) {
    Quote quote = quoteCalculationService.calculateQuote(car);
    return new ResponseEntity<>(carLoanService.saveCarLoanRequest(car, quote), HttpStatus.OK);
  }

  @GetMapping("/creditDecision")
  public ResponseEntity<String> getQuote(@RequestBody CreditDecisionRequest creditDecisionRequest) {
    return new ResponseEntity<>(creditDecisionService.getDecision(creditDecisionRequest), HttpStatus.OK);
  }
}
