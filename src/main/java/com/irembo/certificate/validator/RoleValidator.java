package com.irembo.certificate.validator;

import com.irembo.certificate.exceptions.ValidationException;
import com.irembo.certificate.models.Role;
import com.irembo.certificate.repositories.RoleRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleValidator {
  private final RoleRepository roleRepository;

  public void validateRoleCreate(Role role) {
    if (StringUtils.isEmpty(role.name())) {
      throw new ValidationException("Role name can not be empty");
    }
    var roleOptional = roleRepository.findByName(role.name());
    if (roleOptional.isPresent()) {
      throw new ValidationException(String.format("Role with %s name is exists", role.name()));
    }
  }
}
