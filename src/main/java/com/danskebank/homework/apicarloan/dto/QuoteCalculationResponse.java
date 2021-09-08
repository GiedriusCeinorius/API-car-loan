package com.danskebank.homework.apicarloan.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuoteCalculationResponse {

  private Long id;
  private BigDecimal quote;

  public QuoteCalculationResponse(Long id, BigDecimal quote) {
    this.id = id;
    this.quote = quote;
  }
}
