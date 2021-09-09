package com.danskebank.homework.apicarloan.exception;

import com.danskebank.homework.apicarloan.enums.ApiErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String field = null;
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      field = error.getField();
    }
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), "Bad argument! " + field, ApiErrorCode.APIERROR001);
    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
  }
//
//  @ExceptionHandler(ConstraintViolationException.class)
//  public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
//    CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), ApiErrorCode.API_ERROR001, request.getDescription(false));
//    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
//  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
    CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(), ApiErrorCode.APIERROR001);
    return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
  }

}