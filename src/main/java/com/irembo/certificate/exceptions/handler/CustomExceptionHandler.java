package com.irembo.certificate.exceptions.handler;

import com.irembo.certificate.exceptions.ValidationException;
import com.irembo.certificate.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleValidationException(ValidationException e) {
    return new ErrorResponse(e.getMessage());
  }

  @ExceptionHandler(value = {RuntimeException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  protected ErrorResponse handleRuntimeException(RuntimeException e) {
    return new ErrorResponse(e.getMessage());
  }

}
