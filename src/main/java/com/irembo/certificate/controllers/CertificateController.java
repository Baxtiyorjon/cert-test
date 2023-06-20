package com.irembo.certificate.controllers;

import com.irembo.certificate.models.Certificate;
import com.irembo.certificate.models.Fields;
import com.irembo.certificate.services.CertificateService;
import com.irembo.certificate.validator.CertificateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CertificateController {

  private final CertificateService certificateService;
  private final CertificateValidator certificateValidator;

  @PostMapping("/v1/irembo/templates/{templateIdentifier}/certificate")
  public ResponseEntity<Certificate> generateCertificate(
      @PathVariable("templateIdentifier") String templateIdentifier, @RequestBody Fields fields)
      throws IOException {
    var template = certificateValidator.validateCertificateGeneration(fields, templateIdentifier);
    var certificate = certificateService.generateCertificate(template, fields);
    return ResponseEntity.ok(certificate);
  }

  @GetMapping("/v1/irembo/templates/certificates/{certificateIdentifier}")
  public ResponseEntity<Certificate> generateCertificate(
      @PathVariable("certificateIdentifier") String certificateIdentifier) {
    certificateValidator.validateGetCertificate(certificateIdentifier);
    var certificate = certificateService.getCertificate(certificateIdentifier);
    return ResponseEntity.ok(certificate);
  }


}
