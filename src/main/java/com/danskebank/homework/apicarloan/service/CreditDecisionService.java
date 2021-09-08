package com.danskebank.homework.apicarloan.service;

import com.danskebank.homework.apicarloan.dto.CreditDecisionRequest;
import org.springframework.stereotype.Service;

@Service
public class CreditDecisionService {

  public String getDecision(CreditDecisionRequest creditDecisionRequest) {
    return "YES";
  }
}
