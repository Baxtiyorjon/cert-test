package com.irembo.certificate.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Certificate {
  private String certificateIdentifier;
  private String unsignedUrl;
  private CertificateStatus status;
  private Fields fields;
  private String name;
}
