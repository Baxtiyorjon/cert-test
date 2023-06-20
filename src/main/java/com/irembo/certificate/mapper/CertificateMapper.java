package com.irembo.certificate.mapper;

import com.irembo.certificate.entities.CertificateEntity;
import com.irembo.certificate.models.Certificate;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public interface CertificateMapper {
  CertificateEntity toEntity(Certificate dto);
  Certificate toDTO(CertificateEntity entity);
}
