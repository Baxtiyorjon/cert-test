package com.irembo.certificate.controllers;

import com.irembo.certificate.models.Role;
import com.irembo.certificate.services.RoleService;
import com.irembo.certificate.validator.RoleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/irembo/roles")
public class RoleController {
  private final RoleService roleService;
  private final RoleValidator roleValidator;

  @PostMapping
  public ResponseEntity<Role> createRole(@RequestBody Role role) {
     roleValidator.validateRoleCreate(role);
     var newRole = roleService.create(role);
     return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
  }
}
