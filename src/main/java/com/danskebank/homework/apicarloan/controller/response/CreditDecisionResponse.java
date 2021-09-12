package com.danskebank.homework.apicarloan.controller.response;

import lombok.Data;

@Data
public class CreditDecisionResponse {

  private String decision;

  public CreditDecisionResponse(String decision) {
    this.decision = decision;
  }
}
