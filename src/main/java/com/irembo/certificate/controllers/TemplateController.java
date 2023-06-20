package com.irembo.certificate.controllers;

import com.irembo.certificate.models.Template;
import com.irembo.certificate.models.TemplateRequest;
import com.irembo.certificate.services.TemplateService;
import com.irembo.certificate.validator.TemplateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/irembo/template")
@RequiredArgsConstructor
public class TemplateController {
  private final TemplateService templateService;
  private final TemplateValidator templateValidator;

  @PostMapping("/upload")
  public ResponseEntity<Template> uploadFile( @RequestBody TemplateRequest request)  {
    templateValidator.validateTemplate(request);
    var template = templateService.saveTemplate(request);
    return ResponseEntity.ok(template);
  }

}
