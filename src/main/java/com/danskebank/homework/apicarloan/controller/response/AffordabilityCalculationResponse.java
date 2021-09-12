package com.danskebank.homework.apicarloan.controller.response;

import lombok.Data;

@Data
public class AffordabilityCalculationResponse {

  private Long affordabilityId;

  public AffordabilityCalculationResponse(Long affordabilityId) {
    this.affordabilityId = affordabilityId;
  }
}
