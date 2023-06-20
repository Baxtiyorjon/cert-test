package com.irembo.certificate.exceptions;

public class CertificateCustomException extends RuntimeException {
  public CertificateCustomException(String message) {
    super(message);
  }

  public CertificateCustomException(String message, Throwable cause) {
    super(message, cause);
  }
}
