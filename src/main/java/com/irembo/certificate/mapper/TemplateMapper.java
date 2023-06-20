package com.irembo.certificate.mapper;

import com.irembo.certificate.entities.TemplateEntity;
import com.irembo.certificate.entities.UserEntity;
import com.irembo.certificate.models.Template;
import com.irembo.certificate.models.User;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public interface TemplateMapper {
  TemplateEntity toEntity(Template dto);
  Template toDTO(TemplateEntity entity);
}
