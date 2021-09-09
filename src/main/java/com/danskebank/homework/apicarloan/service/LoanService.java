package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Car;


public interface LoanService {

  QuoteCalculationResponse getQuote(Car car);

  String getDecision(Long affordabilityId, Long quoteId);
}
