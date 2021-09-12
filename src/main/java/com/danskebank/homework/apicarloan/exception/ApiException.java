package com.danskebank.homework.apicarloan.exception;

import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException{

  private final ApiErrorCode apiErrorCode;
  private final HttpStatus httpStatus;

  public ApiException(ApiErrorCode apiErrorCode) {
    this(apiErrorCode.getErrorMessage(), apiErrorCode, HttpStatus.BAD_REQUEST);
  }

  public ApiException(String message, ApiErrorCode apiErrorCode, HttpStatus httpStatus) {
    super(message);
    this.apiErrorCode = apiErrorCode;
    this.httpStatus = httpStatus;
  }

}
