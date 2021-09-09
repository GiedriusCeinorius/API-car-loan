package com.danskebank.homework.apicarloan.exception;

import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomErrorDetails {

  private LocalDateTime timestamp;
  private String message;
  private ApiErrorCode apiErrorCode;

  public CustomErrorDetails(LocalDateTime timestamp, String message, ApiErrorCode apiErrorCode) {
    this.timestamp = timestamp;
    this.message = message;
    this.apiErrorCode = apiErrorCode;
  }
}
