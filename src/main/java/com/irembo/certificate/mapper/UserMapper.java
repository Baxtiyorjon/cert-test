package com.irembo.certificate.mapper;

import com.irembo.certificate.entities.UserEntity;
import com.irembo.certificate.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
  UserEntity toEntity(User dto);
  User toDTO(UserEntity entity);
}
