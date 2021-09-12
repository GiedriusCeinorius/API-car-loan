package com.danskebank.homework.apicarloan.enums;

public enum ApiErrorCode {

  API_ERROR_001("Bank margin not found!"),
  API_ERROR_002("Affordability not found!"),
  API_ERROR_003("Quote not found!"),
  API_ERROR_004("Illegal argument!");

  private String errorMessage;

  ApiErrorCode(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
