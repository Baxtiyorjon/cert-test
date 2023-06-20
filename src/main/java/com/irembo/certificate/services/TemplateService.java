package com.irembo.certificate.services;

import com.irembo.certificate.entities.TemplateEntity;
import com.irembo.certificate.mapper.TemplateMapper;
import com.irembo.certificate.models.Template;
import com.irembo.certificate.models.TemplateRequest;
import com.irembo.certificate.repositories.TemplateRepository;
import com.irembo.certificate.security.service.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TemplateService {
  private final TemplateRepository templateRepository;
  private final TemplateMapper templateMapper;
  private final JwtUtils jwtUtils;

  public Template saveTemplate(TemplateRequest request) {
    var entity = new TemplateEntity();
    entity.setName(request.getName());
    entity.setSource(request.getSource());
    entity.setTemplateIdentifier(UUID.randomUUID().toString());
    entity.setUser(jwtUtils.getCurrentUser());
    entity.setPlaceholders(request.getPlaceholders());
    var savedEntity = templateRepository.save(entity);
    return templateMapper.toDTO(savedEntity);
  }


}
