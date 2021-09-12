package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.controller.response.CreditDecisionResponse;
import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Car;


public interface LoanService {

  QuoteCalculationResponse createQuote(Car car);

  CreditDecisionResponse getDecision(Long affordabilityId, Long quoteId);
}
