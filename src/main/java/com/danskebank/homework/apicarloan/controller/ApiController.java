package com.danskebank.homework.apicarloan.controller;

import com.danskebank.homework.apicarloan.controller.response.AffordabilityCalculationResponse;
import com.danskebank.homework.apicarloan.controller.response.CreditDecisionResponse;
import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.dto.ApplicantDto;
import com.danskebank.homework.apicarloan.dto.CarDto;
import com.danskebank.homework.apicarloan.mapper.ApplicantMapper;
import com.danskebank.homework.apicarloan.mapper.CarMapper;
import com.danskebank.homework.apicarloan.service.AffordabilityServiceFactory;
import com.danskebank.homework.apicarloan.service.CarLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/loan")
public class ApiController {

  private final AffordabilityServiceFactory affordabilityServiceFactory;
  private final CarLoanService carLoanService;
  private final ApplicantMapper applicantMapper;
  private final CarMapper carMapper;

  public ApiController(AffordabilityServiceFactory affordabilityServiceFactory,
                       CarLoanService carLoanService,
                       ApplicantMapper applicantMapper,
                       CarMapper carMapper) {
    this.affordabilityServiceFactory = affordabilityServiceFactory;
    this.carLoanService = carLoanService;
    this.applicantMapper = applicantMapper;
    this.carMapper = carMapper;
  }

  @PostMapping("/affordabilityCalculation")
  public ResponseEntity<AffordabilityCalculationResponse> createAffordability(@Valid @RequestBody ApplicantDto applicant) {
    return new ResponseEntity<>(affordabilityServiceFactory.getAffordabilityService(applicantMapper.toEntity(applicant)).createAffordability(), HttpStatus.OK);
  }

  @PostMapping("/quoteCalculation")
  public ResponseEntity<QuoteCalculationResponse> createQuote(@Valid @RequestBody CarDto car) {
    return new ResponseEntity<>(carLoanService.createQuote(carMapper.toEntity(car)), HttpStatus.OK);
  }

  @GetMapping("/creditDecision/{affordabilityId}/{quoteId}")
  public ResponseEntity<CreditDecisionResponse> getDecision(@PathVariable(name = "affordabilityId") Long affordabilityId, @PathVariable(name = "quoteId") Long quoteId) {
    return new ResponseEntity<>(carLoanService.getDecision(affordabilityId, quoteId), HttpStatus.OK);
  }
}
