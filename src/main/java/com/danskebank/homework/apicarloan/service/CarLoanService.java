package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.controller.response.QuoteCalculationResponse;
import com.danskebank.homework.apicarloan.domain.Affordability;
import com.danskebank.homework.apicarloan.domain.BankMargin;
import com.danskebank.homework.apicarloan.domain.Car;
import com.danskebank.homework.apicarloan.domain.Quote;
import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import com.danskebank.homework.apicarloan.exception.ApiException;
import com.danskebank.homework.apicarloan.repository.AffordabilityRepository;
import com.danskebank.homework.apicarloan.repository.BankMarginRepository;
import com.danskebank.homework.apicarloan.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CarLoanService implements LoanService {

  public static final BigDecimal COEFFICIENT = BigDecimal.valueOf(1);

  private final QuoteRepository quoteRepository;
  private final AffordabilityRepository affordabilityRepository;
  private final BankMarginRepository bankMarginRepository;

  public CarLoanService(QuoteRepository quoteRepository,
                        AffordabilityRepository affordabilityRepository,
                        BankMarginRepository bankMarginRepository) {
    this.quoteRepository = quoteRepository;
    this.affordabilityRepository = affordabilityRepository;
    this.bankMarginRepository = bankMarginRepository;
  }

  public QuoteCalculationResponse getQuote(Car car) {
    BankMargin bankMargin = bankMarginRepository.findById(1L).orElseThrow(() -> new ApiException(ApiErrorCode.API_ERROR_001));
    Quote quote = new Quote(calculateQuote(car, bankMargin));
    quote.setCar(car);
    Quote savedQuote = quoteRepository.save(quote);
    return new QuoteCalculationResponse(savedQuote.getId(), savedQuote.getQuoteValue());
  }

  private BigDecimal calculateQuote(Car car, BankMargin bankMargin) {
    return (car.getPrice().divide(BigDecimal.valueOf(car.getLoanLength()), RoundingMode.HALF_UP)).multiply(COEFFICIENT.add(bankMargin.getBankMarginValue()));
  }

  public String getDecision(Long affordabilityId, Long quoteId) {
    Affordability affordability = affordabilityRepository.findById(affordabilityId).orElseThrow(() -> new ApiException(ApiErrorCode.API_ERROR_002));
    Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new ApiException(ApiErrorCode.API_ERROR_003));
    List<Quote> quotes = affordability.getQuotes();
    quotes.add(quote);
    quote.setAffordability(affordability);
    quoteRepository.save(quote);
    return "YES";
  }
}
