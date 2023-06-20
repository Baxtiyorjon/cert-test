package com.irembo.certificate.services;

import com.irembo.certificate.mapper.RoleMapper;
import com.irembo.certificate.models.Role;
import com.irembo.certificate.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleService {
  private final RoleRepository roleRepository;
  public final RoleMapper roleMapper;

  public Role create(Role role) {
    var entity = roleMapper.toEntity(role);
    var savedEntity = roleRepository.save(entity);
    return roleMapper.toDTO(savedEntity);
  }
}
