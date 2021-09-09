package com.danskebank.homework.apicarloan.exception;

import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException{

  private ApiErrorCode apiErrorCode;
  private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

  public ApiException() {
    super();
  }

  public ApiException(ApiErrorCode apiErrorCode) {
    this(apiErrorCode.getErrorMessage(), apiErrorCode, HttpStatus.BAD_REQUEST);
  }

  public ApiException(ApiErrorCode apiErrorCode, HttpStatus httpStatus) {
    this(apiErrorCode.getErrorMessage(), apiErrorCode, httpStatus);
  }

  public ApiException(String message, ApiErrorCode apiErrorCode, HttpStatus httpStatus) {
    super(message);
    this.apiErrorCode = apiErrorCode;
    this.httpStatus = httpStatus;
  }

}
