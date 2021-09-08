package com.danskebank.homework.apicarloan.dto;

import lombok.Data;

@Data
public class CreditDecisionRequest {

  private Long affordabilityId;
  private Long quoteId;
}
