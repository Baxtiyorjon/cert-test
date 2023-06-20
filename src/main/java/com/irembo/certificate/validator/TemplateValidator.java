package com.irembo.certificate.validator;

import com.amazonaws.util.CollectionUtils;
import com.amazonaws.util.StringUtils;
import com.irembo.certificate.constant.Constants;
import com.irembo.certificate.exceptions.ValidationException;
import com.irembo.certificate.models.TemplateRequest;
import org.springframework.stereotype.Component;

@Component
public class TemplateValidator {

  public void validateTemplate(TemplateRequest templateRequest) {
    if (StringUtils.isNullOrEmpty(templateRequest.getSource())) {
      throw new ValidationException("html can not be empty");
    }
    if (StringUtils.isNullOrEmpty(templateRequest.getName())) {
      throw new ValidationException("name can not be empty");
    }
    if (CollectionUtils.isNullOrEmpty(templateRequest.getPlaceholders())) {
      throw new ValidationException("Template should have at least one placeholder");
    }
    if (!templateRequest.getPlaceholders().contains(Constants.CERTIFICATE_IDENTIFIER)) {
      throw new ValidationException("Template must have 'CERTIFICATE_IDENTIFIER' placeholder");
    }
  }
}
