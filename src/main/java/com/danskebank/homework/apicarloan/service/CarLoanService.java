package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.domain.Quote;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;
import com.danskebank.homework.apicarloan.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarLoanService implements LoanService {

  private final QuoteRepository quoteRepository;
  private final AffordabilityRepository affordabilityRepository;

  public CarLoanService(QuoteRepository quoteRepository,
                        AffordabilityRepository affordabilityRepository) {
    this.quoteRepository = quoteRepository;
    this.affordabilityRepository = affordabilityRepository;
  }

  public QuoteCalculationResponse getQuote(Car car) {
    Quote quote = new Quote(BigDecimal.valueOf(3));
    quote.setCar(car);
    Quote savedQuote = quoteRepository.save(quote);
    return new QuoteCalculationResponse(savedQuote.getId(), savedQuote.getQuote());
  }

  public String getDecision(Long affordabilityId, Long quoteId) {
    Affordability affordability = affordabilityRepository.findById(affordabilityId).get();
    Quote quote = quoteRepository.findById(quoteId).get();
    List<Quote> quotes = affordability.getQuotes();
    quotes.add(quote);
    quote.setAffordability(affordability);
    quoteRepository.save(quote);
    return "YES";
  }
}
