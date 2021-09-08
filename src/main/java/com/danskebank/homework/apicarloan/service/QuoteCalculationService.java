package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.domain.Quote;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class QuoteCalculationService {


  public Quote calculateQuote(Car car) {
    return new Quote(BigDecimal.valueOf(3));
  }
}
