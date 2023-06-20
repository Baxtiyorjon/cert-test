package com.irembo.certificate.validator;

import com.irembo.certificate.entities.TemplateEntity;
import com.irembo.certificate.exceptions.ValidationException;
import com.irembo.certificate.models.Fields;
import com.irembo.certificate.repositories.CertificateRepository;
import com.irembo.certificate.repositories.TemplateRepository;
import com.irembo.certificate.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CertificateValidator {
  private final TemplateRepository templateRepository;
  private final JwtUtils jwtUtils;
  private final CertificateRepository certificateRepository;

  public TemplateEntity validateCertificateGeneration(Fields fields, String templateIdentifier) {
    var templateOptional = templateRepository.findById(templateIdentifier);
    if (templateOptional.isEmpty()) {
      throw new ValidationException(
          String.format("No template has been found. Template identifier: %s ",
              templateIdentifier));
    }
    var template = templateOptional.get();

    if (!Objects.equals(template.getUser().getId(), jwtUtils.getCurrentUser().getId())) {
      throw new ValidationException("Only owner of this template can generate certificate");
    }
    for (String field : fields.getValues().keySet()) {
      if (!template.getPlaceholders().contains(field)) {
        throw new ValidationException(
            String.format("Field with this keyword %s has not been found among placeholders",
                field));
      }
    }
    return template;
  }

  public void validateGetCertificate(String certificateIdentifier) {
    var certificateOptional = certificateRepository.findById(certificateIdentifier);
    if (certificateOptional.isEmpty()) {
      throw new ValidationException(
          String.format("No certificate has been found. Certificate identifier: %s ",
              certificateIdentifier));
    }
    var certificate = certificateOptional.get();
    if (!Objects.equals(certificate.getUser().getId(), jwtUtils.getCurrentUser().getId())) {
      throw new ValidationException("Only owner of this certificate can see generated certificate");
    }
  }
}
