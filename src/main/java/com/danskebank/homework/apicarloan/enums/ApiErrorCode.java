package com.danskebank.homework.apicarloan.enums;

public enum ApiErrorCode {

  APIERROR001("Message");

  private String errorMessage;

  ApiErrorCode(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
