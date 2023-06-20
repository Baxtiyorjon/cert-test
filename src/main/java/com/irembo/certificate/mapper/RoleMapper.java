package com.irembo.certificate.mapper;

import com.irembo.certificate.entities.RoleEntity;
import com.irembo.certificate.models.Role;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public interface RoleMapper {

  RoleEntity toEntity(Role dto);
  Role toDTO(RoleEntity entity);
}
