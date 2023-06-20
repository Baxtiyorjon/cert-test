package com.irembo.certificate.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateData {
  private String templateIdentifier;
  private Fields fields;
  private String certificateIdentifier;
}
